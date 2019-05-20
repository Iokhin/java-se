package ru.iokhin.tm.repository;

import lombok.RequiredArgsConstructor;
import ru.iokhin.tm.api.repository.IAbstractRepository;
import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AbstractRepository<T extends AbstractEntity> implements IAbstractRepository<T> {

    final Map<String, T> repositoryMap = new LinkedHashMap<>(0);

    @Override
    public T persist(T entity) {
        repositoryMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public T merge(T entity) {
        return persist(entity);
    }

    @Override
    public T remove(String id) {
        return repositoryMap.remove(id);
    }

    @Override
    public void removeAll() {
        repositoryMap.clear();
    }

    @Override
    public T findOne(String id) {
        return repositoryMap.get(id);
    }

    @Override
    public Collection<T> findAll() {
        return repositoryMap.values();
    }
}
