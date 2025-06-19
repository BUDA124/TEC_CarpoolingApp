package org.tec.carpooling.common.exceptions;

public class OperationFailedException extends RuntimeException {
    public OperationFailedException(String message, Exception e) {
        super(message);
    }
}