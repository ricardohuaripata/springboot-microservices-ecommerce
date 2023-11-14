package com.ecommerce.orderservice.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.orderservice.error.ValidationError;
import com.ecommerce.orderservice.response.ErrorResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status,
            String message,
            Map<String, List<ValidationError>> validationErrors) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status)
                .statusCode(status.value())
                .message(message)
                .reason(status.getReasonPhrase())
                .validationErrors(validationErrors)
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException e) {
        Map<String, List<ValidationError>> validationErrors = new HashMap<>();
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = buildErrorResponse(HttpStatus.BAD_REQUEST,
                "Validation Error", validationErrors);

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            List<ValidationError> validationErrorList = Objects
                    .requireNonNull(errorResponseResponseEntity.getBody())
                    .getValidationErrors().get(fieldError.getField());
            if (validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorResponseResponseEntity.getBody().getValidationErrors().put(fieldError.getField(),
                        validationErrorList);
            }
            ValidationError validationError = ValidationError.builder()
                    .code(fieldError.getCode())
                    .message(fieldError.getDefaultMessage())
                    .build();
            validationErrorList.add(validationError);
        }

        return errorResponseResponseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception e) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.INTERNAL_SERVER_ERROR, null);
    }

    // NOT FOUND EXCEPTIONS

    // EXISTS EXCEPTIONS

}
