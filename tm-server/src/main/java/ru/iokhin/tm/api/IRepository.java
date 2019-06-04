package ru.iokhin.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface IRepository<E extends AbstractEntity> {
    E persist(@NotNull final E entity) throws SQLException;

    E merge(@NotNull final E entity);

    E findOne(@NotNull final String id) throws SQLException;

    Collection<E> findAll() throws SQLException;

    E remove(@NotNull final String id) throws SQLException;

    void removeAll();

}
