package com.ecommerce.catalogservice.exceptions;

public class HexcodeExistsException extends RuntimeException {
    public HexcodeExistsException() {
    }

    public HexcodeExistsException(String message) {
        super(message);
    }
}
