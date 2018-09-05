package com.example.mongodbdemo.repository.type;

import com.example.mongodbdemo.domain.model.Domain;
import com.example.mongodbdemo.domain.model.DomainType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainTypeRepository extends TypeRepository<DomainType, Domain>, MongoRepository<DomainType, String>,
        TypeEntityRepository<DomainType, Domain> {
}
