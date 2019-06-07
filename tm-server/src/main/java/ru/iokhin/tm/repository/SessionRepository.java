package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.entity.Session;

import javax.persistence.EntityManager;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    public SessionRepository(@NotNull EntityManager em) {
        super(em);
    }

}
