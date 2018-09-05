package com.example.mongodbdemo.service;

import com.example.mongodbdemo.domain.model.TypeEntity;
import com.example.mongodbdemo.domain.model.Type;
import com.example.mongodbdemo.dto.TypeUpdateDto;

import java.util.List;

public interface TypeService<T extends Type<E>, E extends TypeEntity> {

    T findById(String id, Class<T> typeClass);

    T findByName(String name, Class<T> typeClass);

    List<T> findAll(Class<T> typeClass);

    T create(T type);

    T update(T type);

    T update(TypeUpdateDto typeUpdate, Class<T> typeClass, Class<E> entityClass);

    T deleteById(String id, Class<T> typeClass);
}
