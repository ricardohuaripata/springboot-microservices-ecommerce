package com.ecommerce.catalogservice.exceptions;

public class ColorProductVariantExistsException extends RuntimeException {
    public ColorProductVariantExistsException() {
    }

    public ColorProductVariantExistsException(String message) {
        super(message);
    }
}
