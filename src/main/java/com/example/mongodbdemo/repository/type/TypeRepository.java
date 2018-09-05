package com.example.mongodbdemo.repository.type;

import com.example.mongodbdemo.domain.model.TypeEntity;
import com.example.mongodbdemo.domain.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface TypeRepository<T extends Type<E>, E extends TypeEntity> extends MongoRepository<T, String>,
        TypeEntityRepository<T, E> {

    Optional<T> findByName(String name);
}
