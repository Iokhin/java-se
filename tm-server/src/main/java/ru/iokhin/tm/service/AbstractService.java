package ru.iokhin.tm.service;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.AbstractEntity;

import javax.persistence.EntityManagerFactory;


@Getter
@NoArgsConstructor
public abstract class AbstractService<E extends AbstractEntity> implements IService<E> {

    @NotNull
    protected EntityManagerFactory factory;

    public AbstractService(@NotNull EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void persist(@NotNull E entity) {

    }

    @Override
    public abstract void merge(@NotNull E entity);

    @Override
    public abstract E findOne(@NotNull String id);

    @Override
    public abstract void remove(@NotNull E entity);

}

