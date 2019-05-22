package ru.iokhin.tm.api;

import ru.iokhin.tm.entity.AbstractEntity;

public interface IService<E extends AbstractEntity> extends IRepository<E> {

}
