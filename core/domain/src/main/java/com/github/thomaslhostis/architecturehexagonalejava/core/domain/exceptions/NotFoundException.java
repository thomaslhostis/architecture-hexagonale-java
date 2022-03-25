package com.github.thomaslhostis.architecturehexagonalejava.core.domain.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
