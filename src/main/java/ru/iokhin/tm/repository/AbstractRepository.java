package ru.iokhin.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    protected Map<String, E> repository = new LinkedHashMap<>();

    @Override
    public E persist(@NotNull final E entity) {
        return repository.put(entity.getId(), entity);
    }

    @Override
    public E merge(@NotNull final E entity) {
        return persist(entity);
    }

    @Override
    public E findOne(@NotNull final String id) {
        return repository.get(id);
    }

    @Override
    public Collection<E> findAll() {
        return repository.values();
    }

    @Override
    public E remove(@NotNull final String id) {
        return repository.remove(id);
    }

    @Override
    public void removeAll() {
        repository.clear();
    }
}
