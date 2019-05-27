package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.SignatureUtil;

import java.util.Date;

public class SessionService extends AbstractService<Session, ISessionRepository> implements ISessionService {
    public SessionService(ISessionRepository repository) {
        super(repository);
    }

    @Override
    public Session create(@NotNull String userId) {
        @NotNull final int cycle = 100;
        @NotNull final String salt = "13bFyDpqAvMl02";
        @NotNull final Session session = new Session();
        session.setTimeStamp(new Date());
        session.setParentId(userId);
        session.setSignature(SignatureUtil.sign(session, salt, cycle));
        return repository.persist(session);
    }

    @Override
    public void validate(@Nullable Session session) throws AuthException {
        if (session == null)
            throw new AuthException("Session is invalid: \nSession must not be null! Please re-login!");
        if (session.getSignature() == null)
            throw new AuthException("Session is invalid: \nSignature must not be null! Please re-login!");
        if (session.getParentId() == null)
            throw new AuthException("Session is invalid: \nUser must not be null! Please re-login!");
        if (session.getTimeStamp() == null)
            throw new AuthException("Session is invalid: \nTime must not be null! Please re-login!");
        if (findOne(session.getId()) == null)
            throw new AuthException("Session is invalid: \nSession not found! Please re-login!");
        if (!session.getSignature().equals(findOne(session.getId()).getSignature()))
            throw new AuthException("INVALID SESSION");
    }

    @Override
    public boolean validateAdmin(@Nullable Session session) {
        return false;
    }
}
