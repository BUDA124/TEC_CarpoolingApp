package org.tec.carpooling.bl.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
// import org.tec.carpooling.bl.dto.UI_BL.StartUp.UserRegistrationDTO; // Ya no se usa directamente
import org.springframework.beans.factory.annotation.Autowired; // Puede que ya no necesite si el servicio no se usa
import org.tec.carpooling.bl.services.SimpleDataRetrievalService; // Si se remueve su uso, puede quitarse el import
import org.tec.carpooling.bl.validators.constraints.ValidInstitutionalEmail;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ValidInstitutionalEmailValidator implements ConstraintValidator<ValidInstitutionalEmail, String> {

    private static final List<String> FIXED_INSTITUTIONAL_DOMAINS = Arrays.asList(
            "tec.ac.cr",
            "ucr.ac.cr"
    );

    private static final Pattern EMAIL_BASIC_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,63}$",
            Pattern.CASE_INSENSITIVE);


    @Override
    public void initialize(ValidInstitutionalEmail constraintAnnotation) {}

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.trim().isEmpty()) {
            return true;
        }

        // 1. Validación de formato básico
        if (!EMAIL_BASIC_PATTERN.matcher(email.trim()).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid email format.")
                    .addConstraintViolation();
            return false;
        }

        // 2. Validación de dominio institucional (ahora con dominios fijos)
        String actualDomain = email.substring(email.lastIndexOf('@') + 1).toLowerCase();

        boolean isValidDomain = false;
        for (String fixedDomain : FIXED_INSTITUTIONAL_DOMAINS) {
            String normalizedFixedDomain = fixedDomain.toLowerCase();
            if (actualDomain.equals(normalizedFixedDomain) || actualDomain.endsWith("." + normalizedFixedDomain)) {
                isValidDomain = true;
                break;
            }
        }

        if (!isValidDomain) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            String.format("Email domain is not an allowed institutional domain. Allowed domains: %s",
                                    String.join(", ", FIXED_INSTITUTIONAL_DOMAINS)))
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}