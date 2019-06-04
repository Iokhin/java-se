package ru.iokhin.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.AbstractEntity;
import ru.iokhin.tm.util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    protected Map<String, E> repository = new LinkedHashMap<>();

    protected Connection connection;

    abstract E fetch(@Nullable final ResultSet resultSet) throws SQLException;

    @Override
    public E persist(@NotNull final E entity) throws SQLException {
        repository.put(entity.getId(), entity);
        return entity;
    }

    @Override
    @SneakyThrows
    public E merge(@NotNull final E entity) {
        return persist(entity);
    }

    @Override
    public E findOne(@NotNull final String id) throws SQLException {
        return repository.get(id);
    }

    @Override
    public Collection<E> findAll() throws SQLException {
        return repository.values();
    }

    @Override
    public E remove(@NotNull final String id) throws SQLException {
        return repository.remove(id);
    }

    @Override
    public void removeAll() {
        repository.clear();
    }

    public AbstractRepository(Connection connection) {
        this.connection = connection;
    }
}
