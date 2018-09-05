package com.example.mongodbdemo.repository.type;

import com.example.mongodbdemo.domain.model.TypeEntity;
import com.example.mongodbdemo.domain.model.Type;
import com.example.mongodbdemo.dto.TypeUpdateDto;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

@Repository
public class TypeEntityRepositoryImpl<T extends Type<E>, E extends TypeEntity> implements TypeEntityRepository<T, E> {
    private static final String ATTRIBUTES_PREFIX = "attributes.";
    private final MongoTemplate template;

    @Autowired
    public TypeEntityRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public void updateTypeInEntities(T oldType, TypeUpdateDto typeUpdate, Class<E> entityClass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(oldType.getName()));
        Update update = new Update();
        if (!Objects.equals(oldType.getName(), typeUpdate.getName())) {
            update.set("type", typeUpdate.getName());
        }
        if (!isAttributesEqual(oldType.getAttributeNames(), typeUpdate.getOldToNewAttributeMap().values())) {
            for (Map.Entry<String, String> entry : typeUpdate.getOldToNewAttributeMap().entrySet()) {
                if (Strings.isNullOrEmpty(entry.getValue())) {
                    update.unset(ATTRIBUTES_PREFIX + entry.getKey());
                } else if (!Objects.equals(entry.getKey(), entry.getValue())) {
                    update.rename(ATTRIBUTES_PREFIX + entry.getKey(), ATTRIBUTES_PREFIX + entry.getValue());
                }
            }
        }
        template.updateMulti(query, update, entityClass);
    }

    private boolean isAttributesEqual(Collection<String> attr1, Collection<String> attr2) {
        return new HashSet<>(attr1).equals(new HashSet<>(attr2));
    }
}
