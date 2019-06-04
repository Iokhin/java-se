package ru.iokhin.tm.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.Collection;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public abstract class AbstractService<E extends AbstractEntity, R extends IRepository<E>> implements IService<E> {

    protected R repository;

    @Override
    @SneakyThrows
    public E persist(@NotNull final E entity) {
        return repository.persist(entity);
    }

    @Override
    public E merge(@NotNull final E entity) {
        return repository.merge(entity);
    }

    @Override
    @SneakyThrows
    public E findOne(@NotNull final String id) {
        return repository.findOne(id);
    }

    @Override
    @SneakyThrows
    public Collection<E> findAll() {
        return repository.findAll();
    }

    @Override
    @SneakyThrows
    public E remove(@NotNull final String id) {
        return repository.remove(id);
    }

    @Override
    public void removeAll() {
        repository.removeAll();
    }
}
