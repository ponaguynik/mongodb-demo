package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.domain.model.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDomainRepositoryImpl implements CustomDomainRepository {
    private final MongoTemplate template;

    @Autowired
    public CustomDomainRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public void updateAllType(String oldType, String newType) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(oldType));
        Update update = new Update();
        update.set("type", newType);
        template.updateMulti(query, update, Domain.class);
    }
}
