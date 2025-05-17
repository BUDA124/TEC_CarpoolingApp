package org.tec.carpooling.bl.validators.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.tec.carpooling.bl.validators.MinimumAgeValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = MinimumAgeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinimumAge {
    int value(); // The minimum age
    String message() default "User must be at least {value} years old.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}