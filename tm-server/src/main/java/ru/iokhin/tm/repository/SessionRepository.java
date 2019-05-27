package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.entity.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {
    @Override
    public Collection<Session> findAllByUserId(@NotNull String userId) {
        List<Session> sessionArrayList = new ArrayList<>(0);
        for (Session session : findAll()) {
            if (session.getParentId().equals(userId))
                sessionArrayList.add(session);
        }
        return sessionArrayList;
    }
}
