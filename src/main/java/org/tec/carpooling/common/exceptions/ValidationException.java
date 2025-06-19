package org.tec.carpooling.common.exceptions;

import jakarta.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {
    private final Set<String> violations;

    public ValidationException(Set<String> violations) {
        // 1. Llama al constructor padre con un mensaje estático y genérico.
        //    Esta llamada ya no depende de ningún campo de esta clase.
        super("La entidad contiene errores de validación.");

        // 2. Ahora que el padre ha sido construido, inicializa tus propios campos.
        this.violations = violations;
    }

    public Set<String> getViolations() {
        return violations;
    }

    private static <T> String formatViolations(Set<ConstraintViolation<T>> violations) {
        if (violations == null || violations.isEmpty()) {
            return "No violations.";
        }
        return violations.stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                .collect(Collectors.joining(", "));
    }
}