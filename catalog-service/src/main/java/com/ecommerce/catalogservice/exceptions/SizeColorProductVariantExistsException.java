package com.ecommerce.catalogservice.exceptions;

public class SizeColorProductVariantExistsException extends RuntimeException {
    public SizeColorProductVariantExistsException() {
    }

    public SizeColorProductVariantExistsException(String message) {
        super(message);
    }
}