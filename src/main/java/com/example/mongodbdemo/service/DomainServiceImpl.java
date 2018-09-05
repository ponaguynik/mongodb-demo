package com.example.mongodbdemo.service;

import com.example.mongodbdemo.domain.model.Domain;
import com.example.mongodbdemo.domain.model.DomainType;
import com.example.mongodbdemo.exception.DomainNotFound;
import com.example.mongodbdemo.exception.UnknownDomainAttribute;
import com.example.mongodbdemo.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainServiceImpl implements DomainService {
    private final DomainRepository repository;
    private final TypeService<DomainType, Domain> typeService;

    @Autowired
    public DomainServiceImpl(DomainRepository repository, TypeService<DomainType, Domain> typeService) {
        this.repository = repository;
        this.typeService = typeService;
    }

    @Override
    public Domain findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DomainNotFound("id", id));
    }

    @Override
    public List<Domain> findAll() {
        return repository.findAll();
    }

    @Override
    public Domain create(Domain domain) {
        domain.setId(null);
        resolveAttributeMapping(domain);
        return repository.save(domain);
    }

    @Override
    public Domain update(Domain domain) {
        findById(domain.getId());
        resolveAttributeMapping(domain);
        return repository.save(domain);
    }

    @Override
    public Domain deleteById(String id) {
        Domain domain = findById(id);
        repository.deleteById(id);
        return domain;
    }

    private void resolveAttributeMapping(Domain domain) {
        DomainType type = typeService.findByName(domain.getType(), DomainType.class);
        domain.setType(type.getName());
        for (String attributeName : domain.getAttributeNames()) {
            if (!type.getAttributeNames().contains(attributeName)) {
                throw new UnknownDomainAttribute(attributeName, type.getName());
            }
        }
        List<String> typeAttributesCopy = type.getAttributeNames();
        typeAttributesCopy.removeAll(domain.getAttributeNames());
        typeAttributesCopy.forEach(attr -> domain.setAttribute(attr, null));
    }
}
