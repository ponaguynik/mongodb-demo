package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.domain.model.Domain;
import com.example.mongodbdemo.domain.model.DomainType;
import com.example.mongodbdemo.dto.TypeUpdateDto;
import com.example.mongodbdemo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domain-types")
public class DomainTypeController {
    private final TypeService<DomainType, Domain> service;

    @Autowired
    public DomainTypeController(TypeService<DomainType, Domain> service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DomainType> create(@RequestBody DomainType domainType) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(domainType));
    }

    @RequestMapping(path = "/{typeId}", method = RequestMethod.GET)
    public DomainType findById(@PathVariable String typeId) {
        return service.findById(typeId, DomainType.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DomainType> findAll() {
        return service.findAll(DomainType.class);
    }

    @RequestMapping(path = "/{typeId}", method = RequestMethod.PUT)
    public DomainType update(@PathVariable String typeId, @RequestBody TypeUpdateDto typeUpdateDto) {
        typeUpdateDto.setId(typeId);
        return service.update(typeUpdateDto, DomainType.class, Domain.class);
    }
}
