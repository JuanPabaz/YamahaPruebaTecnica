package com.yamaha.prueba.exceptions;

public class BadCreateRequest extends RuntimeException {

    public BadCreateRequest(String message) {
        super(message);
    }
}
