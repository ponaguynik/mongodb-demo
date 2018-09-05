package com.example.mongodbdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DomainOfTypeExist extends RuntimeException {

    public DomainOfTypeExist(String message) {
        super(message);
    }
}
