package ru.iokhin.tm.service;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.AbstractEntityDTO;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.exeption.AuthException;

@Getter
public abstract class AbstractService<E extends AbstractEntityDTO> implements IService<E> {

    public void persist(@NotNull E entity) throws AuthException {

    }

    public abstract void merge(@NotNull E entity) throws AuthException;

    public abstract E findOne(@NotNull String id);

    public abstract void remove(@NotNull E entity) throws Exception;

}

