package com.example.mongodbdemo.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Type<E extends TypeEntity> {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private List<String> attributeNames = new ArrayList<>();

    public Type() {
    }

    Type(Type<E> type) {
        this.id = type.getId();
        this.name = type.getName();
        this.attributeNames.addAll(type.getAttributeNames());
    }

    public abstract Type<E> copy();
}
