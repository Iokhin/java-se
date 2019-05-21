package ru.iokhin.tm.repository;

import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    protected Map<String, E> repository = new LinkedHashMap<>();

    @Override
    public E persist(E entity) {
        return repository.put(entity.getId(), entity);
    }

    @Override
    public E merge(E entity) {
        return persist(entity);
    }

    @Override
    public E findOne(String id) {
        return repository.get(id);
    }

    @Override
    public Collection<E> findAll() {
        return repository.values();
    }

    @Override
    public E remove(String id) {
        return repository.remove(id);
    }

    @Override
    public void removeAll() {
        repository.clear();
    }
}
