package com.example.mongodbdemo.service;

import java.util.List;

public interface CrudService<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T create(T entity);

    T update(T entity);

    T deleteById(ID id);
}
