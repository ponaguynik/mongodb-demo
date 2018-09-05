package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.domain.model.Domain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DomainRepository extends MongoRepository<Domain, String>, CustomDomainRepository {

}
