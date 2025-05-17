package org.tec.carpooling.bl.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tec.carpooling.bl.validators.constraints.PasswordComplexity;

import java.util.regex.Pattern;

public class PasswordComplexityValidator implements ConstraintValidator<PasswordComplexity, String> {

    // Same pattern as before
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_\\-~`()\\[\\]{}\\|;:'\",.<>/?]).{8,}$");

    @Override
    public void initialize(PasswordComplexity constraintAnnotation) {
        // No initialization needed from annotation attributes
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return false; // Or false if password is required, handle with @NotBlank separately
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}