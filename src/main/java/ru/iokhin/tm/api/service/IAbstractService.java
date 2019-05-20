package ru.iokhin.tm.api.service;

import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;

public interface IAbstractService<T> {

    T remove(final String id);
    void clear();
    T findOne(final String id);
    Collection<T> findAll();

}
