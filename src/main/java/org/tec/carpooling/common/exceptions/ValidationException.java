package org.tec.carpooling.common.exceptions;

import jakarta.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {
    private final Set<? extends ConstraintViolation<?>> violations;

    public <T> ValidationException(String message, Set<ConstraintViolation<T>> violations) {
        super(message + ": " + formatViolations(violations));
        this.violations = violations;
    }

    public Set<? extends ConstraintViolation<?>> getViolations() {
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