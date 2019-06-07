package ru.iokhin.tm.repository;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.AbstractEntity;

import javax.persistence.EntityManager;

@Getter
public abstract class AbstractRepository<E extends AbstractEntity> implements IRepository<E> {

    @NotNull
    protected EntityManager em;

    protected Class<E> entityClass;

    AbstractRepository(@NotNull final EntityManager em) {
        this.em = em;
    }

    @Override
    public void persist(@NotNull final E entity) {
        em.persist(entity);
    }

    @Override
    @SneakyThrows
    public void merge(@NotNull final E entity) {
        em.merge(entity);
    }

    @Override
    public E findOne(@NotNull final String id) {
        return em.find(entityClass, id);
    }

    @Override
    public void remove(@NotNull final E entity) {
        em.remove(entity);
    }

}
