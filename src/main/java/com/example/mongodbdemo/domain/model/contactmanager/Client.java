package com.example.mongodbdemo.domain.model.contactmanager;

import com.example.mongodbdemo.domain.model.TypeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection = "clients")
public class Client extends TypeEntity {
    private LocalDateTime registrationDate;
    private List<InsuranceObject> insuranceObjects;

    public static Client create() {
        Client client = new Client();
        client.registrationDate = LocalDateTime.now();
        return client;
    }
}
