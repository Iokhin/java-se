package ru.iokhin.tm.service;

import lombok.AllArgsConstructor;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;

@AllArgsConstructor
public class AbstractService<E extends AbstractEntity, R extends IRepository<E>> implements IService<E> {

    protected R repository;

    @Override
    public E persist(E entity) {
        return repository.persist(entity);
    }

    @Override
    public E merge(E entity) {
        return repository.merge(entity);
    }

    @Override
    public E findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public Collection<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E remove(String id) {
        return repository.remove(id);
    }

    @Override
    public void removeAll() {
        repository.removeAll();
    }
}
