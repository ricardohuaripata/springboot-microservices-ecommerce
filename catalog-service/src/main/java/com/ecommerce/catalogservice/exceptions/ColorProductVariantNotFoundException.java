package com.ecommerce.catalogservice.exceptions;

public class ColorProductVariantNotFoundException extends RuntimeException {
    public ColorProductVariantNotFoundException() {
    }

    public ColorProductVariantNotFoundException(String message) {
        super(message);
    }
}