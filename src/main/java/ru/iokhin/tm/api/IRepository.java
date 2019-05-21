package ru.iokhin.tm.api;

import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;

public interface IRepository<E extends AbstractEntity> {
    E persist(E entity);

    E merge(E entity);

    E findOne(String id);

    Collection<E> findAll();

    E remove(String id);

    void removeAll();
}
