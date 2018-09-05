package com.example.mongodbdemo.exception;

import com.example.mongodbdemo.domain.model.DomainType;

public class TypeNotUniqueException extends ResourceNotUniqueException {

    public TypeNotUniqueException(String type) {
        super(DomainType.class.getSimpleName(), "type", type);
    }
}
