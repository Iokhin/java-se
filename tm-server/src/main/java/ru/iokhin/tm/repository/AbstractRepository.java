package ru.iokhin.tm.repository;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.AbstractEntity;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

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
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
        return em.find(entityClass, id);
    }

    @Override
    public void remove(@NotNull final E entity) {
        @NotNull final E foundEnity = findOne(entity.getId());
        em.remove(foundEnity);
    }

}
