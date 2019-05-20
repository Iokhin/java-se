package ru.iokhin.tm.api.repository;

import java.util.Collection;

public interface IAbstractRepository<T> {
    T persist(final T entity);

    T merge(final T entity);

    T remove(final String id);

    void removeAll();

    T findOne(String id);

    Collection<T> findAll();
}
