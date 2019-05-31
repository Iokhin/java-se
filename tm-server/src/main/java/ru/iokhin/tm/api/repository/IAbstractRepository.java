package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;

public interface IAbstractRepository<E extends AbstractEntity> {
    E persist(@NotNull final E entity);

    E merge(@NotNull final E entity);

    Collection<E> findAll();
}
