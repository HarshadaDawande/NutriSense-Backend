package com.tw.nutrisense.middleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Intercepts {@link MethodArgumentNotValidException}s thrown when @Valid annotated
 * request bodies fail Bean Validation.  Converts the verbose default Spring error
 * payload into a compact structure mapping each invalid field to its error message.
 *
 * Example response:
 * {
 *     "emailAddress": "Email address is invalid",
 *     "userName": "must not be blank"
 * }
 */
@RestControllerAdvice
public class ValidationErrorHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        err -> err.getField(),
                        err -> err.getDefaultMessage(),
                        (first, second) -> first   // keep the first message per field
                ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Handle exceptions explicitly thrown with ResponseStatusException (e.g. conflicts, not found)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatus(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of("message", ex.getReason()));
    }
}
