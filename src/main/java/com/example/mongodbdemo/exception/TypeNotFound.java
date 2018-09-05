package com.example.mongodbdemo.exception;

import com.example.mongodbdemo.domain.model.DomainType;

public class TypeNotFound extends ResourceNotFound {

    public TypeNotFound(String field, String value) {
        super(DomainType.class.getSimpleName(), field, value);
    }
}
