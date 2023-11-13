package com.ecommerce.catalogservice.exceptions;

public class SizeColorProductVariantNotFoundException extends RuntimeException {
    public SizeColorProductVariantNotFoundException() {
    }

    public SizeColorProductVariantNotFoundException(String message) {
        super(message);
    }
}