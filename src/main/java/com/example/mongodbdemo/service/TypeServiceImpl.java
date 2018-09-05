package com.example.mongodbdemo.service;

import com.example.mongodbdemo.domain.model.TypeEntity;
import com.example.mongodbdemo.domain.model.Type;
import com.example.mongodbdemo.dto.TypeUpdateDto;
import com.example.mongodbdemo.exception.TypeNotFound;
import com.example.mongodbdemo.repository.type.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@SuppressWarnings("unchecked")
public class TypeServiceImpl<T extends Type<E>, E extends TypeEntity> implements TypeService<T, E> {
    private final Map<Class<T>, TypeRepository<T, E>> repositoryMap;

    @Autowired
    public TypeServiceImpl(Map<String, TypeRepository<T, E>> repositoryMap) {
        this.repositoryMap = resolveRepositoryMap(repositoryMap, Type.class.getPackage().getName());
    }

    @Override
    public T findById(String id, Class<T> typeClass) {
        return repository(typeClass).findById(id).orElseThrow(() -> new TypeNotFound("id", id));
    }

    @Override
    public T findByName(String name, Class<T> typeClass) {
        return repository(typeClass).findByName(name).orElseThrow(() -> new TypeNotFound("name", name));
    }

    @Override
    public List<T> findAll(Class<T> typeClass) {
        return repository(typeClass).findAll();
    }

    @Override
    public T create(T type) {
        type.setId(null);
        return repository((Class<T>) type.getClass()).save(type);
    }

    @Override
    public T update(T type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T update(TypeUpdateDto typeUpdate, Class<T> typeClass, Class<E> entityClass) {
        T type = findById(typeUpdate.getId(), typeClass);
        Type<E> oldType = type.copy();
        addNotMentionedFields(typeUpdate, type);
        updateType(type, typeUpdate);
        T newType = repository(typeClass).save(type);
        repository(typeClass).updateTypeInEntities((T) oldType, typeUpdate, entityClass);
        return newType;
    }

    private void addNotMentionedFields(TypeUpdateDto typeUpdate, Type<E> type) {
        type.getAttributeNames().stream()
                .filter(attr -> !typeUpdate.getOldToNewAttributeMap().containsKey(attr))
                .forEach(attr -> typeUpdate.getOldToNewAttributeMap().put(attr, attr));
    }

    private void updateType(T type, TypeUpdateDto typeUpdate) {
        type.setName(typeUpdate.getName());
        type.getAttributeNames().clear();
        typeUpdate.getOldToNewAttributeMap().values()
                .forEach(attr -> type.getAttributeNames().add(attr));
    }

    @Override
    public T deleteById(String id, Class<T> typeClass) {
        // TODO: 03-Sep-18 check whether entities of this type exist
        T type = findById(id, typeClass);
        repository(typeClass).deleteById(id);
        return type;
    }

    private TypeRepository<T, E> repository(Class<T> typeClass) {
        return Optional.ofNullable(repositoryMap.get(typeClass)).orElseThrow(
                () -> new IllegalArgumentException("No repository found for type class " + typeClass.getName()));
    }

    private Map<Class<T>, TypeRepository<T, E>> resolveRepositoryMap(Map<String, TypeRepository<T, E>> repositoryMap,
                                                                     String typeClassPackage) {
        return repositoryMap.entrySet().stream()
                .collect(HashMap::new,
                        (map, entry) -> map.put(resolveClass(entry.getKey(), typeClassPackage), entry.getValue()),
                        HashMap::putAll);
    }

    @SuppressWarnings("unchecked")
    private Class<T> resolveClass(String repositoryBeanName, String typeClassPackage) {
        String typeClassName = repositoryBeanName.split("Repository")[0];
        typeClassName = firstLetterUppercase(typeClassName);
        try {
            return (Class<T>) Class.forName(typeClassPackage + "." + typeClassName);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("No Type class found for '" + repositoryBeanName + "' repository");
        }
    }

    private String firstLetterUppercase(String typeClassName) {
        return Character.toUpperCase(typeClassName.charAt(0)) + typeClassName.substring(1);
    }

}
