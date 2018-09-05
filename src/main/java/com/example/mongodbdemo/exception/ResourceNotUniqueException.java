package com.example.mongodbdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public abstract class ResourceNotUniqueException extends RuntimeException {
    private static final String MESSAGE_FORMAT = "%s with %s '%s' field already exists";

    protected ResourceNotUniqueException() {
    }

    protected ResourceNotUniqueException(Object name, String field, String value) {
        super(String.format(MESSAGE_FORMAT, name, field, value));
    }
}
