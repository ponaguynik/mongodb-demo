package com.example.mongodbdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnknownDomainAttribute extends RuntimeException{
    private static final String MESSAGE_FORMAT = "Unknown attribute '%s' for domain type '%s'";

    public UnknownDomainAttribute(String attributeName, String domainType) {
        super(String.format(MESSAGE_FORMAT, attributeName, domainType));
    }
}
