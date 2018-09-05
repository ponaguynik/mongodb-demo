package com.example.mongodbdemo.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection = "domain")
public class Domain extends TypeEntity {
    @Id
    private String id;
    private LocalDateTime date;
}
