package ru.iokhin.tm.api;

import ru.iokhin.tm.entity.AbstractEntity;

import java.util.Collection;

public interface IService<E extends AbstractEntity> extends IRepository<E> {

}
