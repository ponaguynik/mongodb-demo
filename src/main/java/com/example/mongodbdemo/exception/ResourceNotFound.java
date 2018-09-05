package com.example.mongodbdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class ResourceNotFound extends RuntimeException {
    private static final String MESSAGE_FORMAT = "%s by %s: '%s' not found";

    protected ResourceNotFound() {
    }

    protected ResourceNotFound(Object name, Object field, Object value) {
        super(String.format(MESSAGE_FORMAT, name, field, value));
    }
}
