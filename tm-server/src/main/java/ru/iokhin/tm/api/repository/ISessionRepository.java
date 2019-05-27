package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Session;

import java.util.Collection;

public interface ISessionRepository extends IRepository<Session> {

    Collection<Session> findAllByUserId(@NotNull final String userId);

}
