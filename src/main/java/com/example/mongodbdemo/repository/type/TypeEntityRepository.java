package com.example.mongodbdemo.repository.type;

import com.example.mongodbdemo.dto.TypeUpdateDto;

public interface TypeEntityRepository<T, E> {

    void updateTypeInEntities(T oldType, TypeUpdateDto typeUpdate, Class<E> entityClass);

}
