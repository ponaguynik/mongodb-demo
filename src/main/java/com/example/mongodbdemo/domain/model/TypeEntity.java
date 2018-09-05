package com.example.mongodbdemo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class TypeEntity {
    @Id
    protected String id;
    protected String type;
    protected Map<String, String> attributes = new HashMap<>();

    @JsonIgnore
    public List<String> getAttributeNames() {
        return new ArrayList<>(attributes.keySet());
    }

    @JsonIgnore
    public void setAttribute(String name, String value) {
        attributes.putIfAbsent(name, value);
    }
}
