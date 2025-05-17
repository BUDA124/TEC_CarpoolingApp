package org.tec.carpooling.bl.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tec.carpooling.bl.validators.constraints.MinimumAge;

import java.time.LocalDate;
import java.time.Period;

public class MinimumAgeValidator implements ConstraintValidator<MinimumAge, LocalDate> {

    private int minAge;

    @Override
    public void initialize(MinimumAge constraintAnnotation) {
        this.minAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
        if (birthdate == null) {
            return true; // Or false if birthdate is required, handle with @NotNull separately
        }
        if (birthdate.isAfter(LocalDate.now())) {
            // Optionally, disable existing default message and add a custom one for future dates
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Birthdate cannot be in the future.")
                   .addConstraintViolation();
            return false;
        }
        return Period.between(birthdate, LocalDate.now()).getYears() >= minAge;
    }
}