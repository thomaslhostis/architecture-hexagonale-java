package com.architecturehexagonale.presentation.configuration;

import com.architecturehexagonale.domain.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
// Cette classe permet de centraliser la gestion des exceptions. Toutes les exceptions
// sont interceptées ici. Important : les exceptions doivent étendre directement ou
// indirectement `RuntimeException`. Cela permet d'éviter de se retrouver avec une
// `UndeclaredThrowableException` qui serait interceptée par la méthode `handleThrowable`
// qui renvoie systématiquement une erreur 503 => https://stackoverflow.com/a/5490372
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<?> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(NOT_FOUND).body(notFoundException.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return ResponseEntity.status(BAD_REQUEST).body(illegalArgumentException.getMessage());
    }

    // Exceptions non gérées
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleUnexpectedException(
            Exception exception,
            WebRequest request
    ) {
        String path = request.getDescription(false);
        logger.error("Unexpected error (path=" + path + "):");
        logger.error(exception);
        exception.printStackTrace();
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        if (INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body != null ? body : exception.getMessage(), headers, status);
    }
}
