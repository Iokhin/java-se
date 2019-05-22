package ru.iokhin.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;

public interface IRepository<E extends AbstractEntity> {
    E persist(@NotNull final E entity);

    E merge(@NotNull final E entity);

    E findOne(@NotNull final String id);

    Collection<E> findAll();

    E remove(@NotNull final String id);

    void removeAll();
}
