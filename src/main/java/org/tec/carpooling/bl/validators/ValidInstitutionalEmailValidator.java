package org.tec.carpooling.bl.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tec.carpooling.bl.dto.UI_BL.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.tec.carpooling.bl.services.SimpleDataRetrievalService;
import org.tec.carpooling.bl.validators.constraints.ValidInstitutionalEmail;

import java.util.regex.Pattern;

public class ValidInstitutionalEmailValidator implements ConstraintValidator<ValidInstitutionalEmail, UserRegistrationDTO> {

    @Autowired
    SimpleDataRetrievalService simpleDataRetrievalService;

    private String emailFieldName;

    private static final Pattern EMAIL_BASIC_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,63}$",
        Pattern.CASE_INSENSITIVE);


    @Override
    public void initialize(ValidInstitutionalEmail constraintAnnotation) {
        this.emailFieldName = constraintAnnotation.emailFieldName();
    }

    @Override
    public boolean isValid(UserRegistrationDTO dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return false;
        }

        String email = dto.getInstitutionalEmail();
        Long institutionId = dto.getIdInstitution();
        
        if (!EMAIL_BASIC_PATTERN.matcher(email.trim()).matches()) {
            return true;
        }

        String expectedPrimaryDomain = simpleDataRetrievalService.getPrimaryDomainForInstitution(institutionId);

        if (expectedPrimaryDomain == null || expectedPrimaryDomain.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Institutional email cannot be verified; institution domain not configured for ID: " + institutionId)
                   .addPropertyNode(emailFieldName)
                   .addConstraintViolation();
            return false;
        }

        String actualDomain = email.substring(email.lastIndexOf('@') + 1).toLowerCase();
        String normalizedExpectedDomain = expectedPrimaryDomain.toLowerCase();

        boolean isValidDomain = actualDomain.equals(normalizedExpectedDomain) || actualDomain.endsWith("." + normalizedExpectedDomain);

        if (!isValidDomain) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    String.format("Email domain must belong to your institution (expected: ...@%s or a subdomain).", expectedPrimaryDomain))
                   .addPropertyNode(emailFieldName)
                   .addConstraintViolation();
            return false;
        }

        return true;
    }
}