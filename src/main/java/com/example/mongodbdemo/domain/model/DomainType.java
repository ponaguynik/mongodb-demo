package com.example.mongodbdemo.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection = "domainType")
public class DomainType extends Type<Domain> {

    public DomainType() {
    }

    private DomainType(DomainType type) {
        super(type);
    }

    @Override
    public DomainType copy() {
        return new DomainType(this);
    }
}
