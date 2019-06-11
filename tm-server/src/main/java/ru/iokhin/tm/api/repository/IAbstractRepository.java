package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.AbstractEntityDTO;

import java.util.Collection;

public interface IAbstractRepository<E extends AbstractEntityDTO> {
    E persist(@NotNull final E entity);

    E merge(@NotNull final E entity);

    Collection<E> findAll();
}
