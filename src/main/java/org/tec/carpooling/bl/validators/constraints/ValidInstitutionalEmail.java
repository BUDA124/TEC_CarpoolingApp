package org.tec.carpooling.bl.validators.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.tec.carpooling.bl.validators.ValidInstitutionalEmailValidator; // Este será modificado

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ValidInstitutionalEmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInstitutionalEmail {
    String message() default "Institutional email domain does not match the selected institution or cannot be verified.";
    String emailFieldName() default "institutionalEmail";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}