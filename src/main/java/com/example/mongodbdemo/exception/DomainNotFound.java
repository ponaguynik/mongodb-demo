package com.example.mongodbdemo.exception;


import com.example.mongodbdemo.domain.model.Domain;

public class DomainNotFound extends ResourceNotFound {

    public DomainNotFound(String field, String value) {
        super(Domain.class.getSimpleName(), field, value);
    }
}
