package com.example.mongodbdemo.domain.model.contactmanager;

import com.example.mongodbdemo.domain.model.TypeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection = "insuranceObject")
public class InsuranceObject extends TypeEntity {

}
