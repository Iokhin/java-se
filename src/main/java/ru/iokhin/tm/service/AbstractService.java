package ru.iokhin.tm.service;

import lombok.AllArgsConstructor;
import ru.iokhin.tm.api.repository.IAbstractRepository;
import ru.iokhin.tm.api.service.IAbstractService;
import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;

@AllArgsConstructor
public class AbstractService<T extends AbstractEntity> implements IAbstractService<T> {

    protected IAbstractRepository<T> repository;

    @Override
    public T remove(String id) {
        return repository.remove(id);
    }

    @Override
    public void clear() {
        repository.removeAll();
    }

    @Override
    public T findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }
}
