package ru.iokhin.tm.repository.old;

import ru.iokhin.tm.api.repository.IAbstractRepository;
import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractRepositoryOLD<E extends AbstractEntity> implements IAbstractRepository<E> {

    final Map<String, E> repositoryMap = new LinkedHashMap<>(0);

    @Override
    public E persist(E entity) {
        repositoryMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public E merge(E entity) {
        return persist(entity);
    }

    @Override
    public Collection<E> findAll() {
        return repositoryMap.values();
    }
}
