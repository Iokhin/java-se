package ru.iokhin.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.AbstractEntity;

public interface IRepository<E extends AbstractEntity> {

    void persist(@NotNull final E entity);

    void merge(@NotNull final E entity);

    E findOne(@NotNull final String id);

    void remove(@NotNull final E entity);


}
