package com.ecommerce.catalogservice.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Locale.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.catalogservice.error.ValidationError;
import com.ecommerce.catalogservice.exceptions.CategoryNotFoundException;
import com.ecommerce.catalogservice.exceptions.SlugExistsException;
import com.ecommerce.catalogservice.response.ErrorResponse;

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

        @ExceptionHandler(CategoryNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e) {
                return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND, null);
        }

        @ExceptionHandler(SlugExistsException.class)
        public ResponseEntity<ErrorResponse> handleSlugExistsException(SlugExistsException e) {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.SLUG_EXISTS, null);
        }

}
