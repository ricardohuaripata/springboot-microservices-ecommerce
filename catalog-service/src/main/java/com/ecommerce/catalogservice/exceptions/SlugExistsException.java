package com.ecommerce.catalogservice.exceptions;

public class SlugExistsException extends RuntimeException {
    public SlugExistsException() {
    }

    public SlugExistsException(String message) {
        super(message);
    }
}
