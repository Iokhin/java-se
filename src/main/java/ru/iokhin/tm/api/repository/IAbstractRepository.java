package ru.iokhin.tm.api.repository;

import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;

public interface IAbstractRepository<E extends AbstractEntity> {
    E persist(final E entity);

    E merge(final E entity);

    Collection<E> findAll();
}
