package com.company.anketi.exception;

import com.haulmont.cuba.core.global.SupportedByClient;

@SupportedByClient
public class AnketiException extends RuntimeException {

    private final String message;

    public AnketiException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
