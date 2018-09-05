package com.example.mongodbdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class TypeUpdateDto {
    private String id;
    private String name;
    private Map<String, String> oldToNewAttributeMap = new HashMap<>();
}
