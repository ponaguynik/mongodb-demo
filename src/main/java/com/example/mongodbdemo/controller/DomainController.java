package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.domain.model.Domain;
import com.example.mongodbdemo.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domains")
public class DomainController {
    private final DomainService service;

    @Autowired
    public DomainController(DomainService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Domain> create(@RequestBody Domain domain) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(domain));
    }

    @RequestMapping(path = "/{domainId}", method = RequestMethod.PUT)
    public Domain update(@PathVariable String domainId, @RequestBody Domain domain) {
        domain.setId(domainId);
        return service.update(domain);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Domain> findAll() {
        return service.findAll();
    }

    @RequestMapping(path = "/{domainId}", method = RequestMethod.GET)
    public Domain findByType(@PathVariable String domainId) {
        return service.findById(domainId);
    }
}
