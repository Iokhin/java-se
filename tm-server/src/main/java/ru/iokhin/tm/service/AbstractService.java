package ru.iokhin.tm.service;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.DTO.AbstractEntityDTO;
import ru.iokhin.tm.api.IService;

import javax.persistence.EntityManagerFactory;


@Getter
@NoArgsConstructor
public abstract class AbstractService<E extends AbstractEntityDTO> implements IService<E> {

    @NotNull
    protected EntityManagerFactory factory;

    public AbstractService(@NotNull EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void persist(@NotNull E entity) {

    }

    public abstract void merge(@NotNull E entity);

    public abstract E findOne(@NotNull String id);

    public abstract void remove(@NotNull E entity);

}

