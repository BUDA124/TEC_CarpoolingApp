package org.tec.carpooling.bl.validators.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.tec.carpooling.bl.validators.PasswordComplexityValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PasswordComplexityValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordComplexity {
    String message() default "Password must be at least 8 characters long and include at least one digit, one lowercase letter, one uppercase letter, and one special character.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}