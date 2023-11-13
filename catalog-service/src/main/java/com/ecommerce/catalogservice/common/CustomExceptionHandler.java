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
import com.ecommerce.catalogservice.exceptions.ColorNotFoundException;
import com.ecommerce.catalogservice.exceptions.ColorProductVariantExistsException;
import com.ecommerce.catalogservice.exceptions.ColorProductVariantNotFoundException;
import com.ecommerce.catalogservice.exceptions.HexcodeExistsException;
import com.ecommerce.catalogservice.exceptions.ProductNotFoundException;
import com.ecommerce.catalogservice.exceptions.SizeColorProductVariantExistsException;
import com.ecommerce.catalogservice.exceptions.SizeColorProductVariantNotFoundException;
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

        // NOT FOUND EXCEPTIONS

        @ExceptionHandler(CategoryNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e) {
                return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND, null);
        }

        @ExceptionHandler(ColorNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleColorNotFoundException(ColorNotFoundException e) {
                return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.COLOR_NOT_FOUND, null);
        }

        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
                return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND, null);
        }

        @ExceptionHandler(ColorProductVariantNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleColorProductVariantNotFoundException(
                        ColorProductVariantNotFoundException e) {
                return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.COLOR_PRODUCT_VARIANT_NOT_FOUND, null);
        }

        @ExceptionHandler(SizeColorProductVariantNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleSizeColorProductVariantNotFoundException(
                        SizeColorProductVariantNotFoundException e) {
                return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.SIZE_COLOR_PRODUCT_VARIANT_NOT_FOUND,
                                null);
        }

        // EXISTS EXCEPTIONS

        @ExceptionHandler(SlugExistsException.class)
        public ResponseEntity<ErrorResponse> handleSlugExistsException(SlugExistsException e) {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.SLUG_EXISTS, null);
        }

        @ExceptionHandler(HexcodeExistsException.class)
        public ResponseEntity<ErrorResponse> handleHexcodeExistsException(HexcodeExistsException e) {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.HEXCODE_EXISTS, null);
        }

        @ExceptionHandler(ColorProductVariantExistsException.class)
        public ResponseEntity<ErrorResponse> handleColorProductVariantExistsException(
                        ColorProductVariantExistsException e) {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.COLOR_PRODUCT_VARIANT_EXISTS, null);
        }

        @ExceptionHandler(SizeColorProductVariantExistsException.class)
        public ResponseEntity<ErrorResponse> handleSizeColorProductVariantExistsException(
                        SizeColorProductVariantExistsException e) {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.SIZE_COLOR_PRODUCT_VARIANT_EXISTS, null);
        }

}
