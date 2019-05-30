package ru.iokhin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.SignatureUtil;

import java.util.Date;

@NoArgsConstructor
public class SessionService extends AbstractService<Session, ISessionRepository> implements ISessionService {

    @NotNull
    private static final int cycle = 100;
    @NotNull
    private static final String salt = "13bFyDpqAvMl02";

    public SessionService(ISessionRepository repository) {
        super(repository);
    }

    @Override
    public Session create(@NotNull String userId) {
        @NotNull final Session session = new Session();
        session.setTimeStamp(new Date());
        session.setParentId(userId);
        sign(session);
        return repository.persist(session);
    }

    @Override
    public void validate(@Nullable Session session) throws AuthException {
        if (session == null) throw new AuthException();
        if (session.getSignature() == null) throw new AuthException();
        if (session.getParentId() == null) throw new AuthException();
        if (session.getTimeStamp() == null) throw new AuthException();
        if (findOne(session.getId()) == null) throw new AuthException();
        if (!session.getSignature().equals(findOne(session.getId()).getSignature()))
            throw new AuthException("INVALID SESSION");
        final Session temp = session.clone();
        temp.setSignature(null);
    }

    @Override
    public String sign(@NotNull Session session) {
        session.setSignature(null);
        session.setSignature(SignatureUtil.sign(session, salt, cycle));
        return session.getSignature();
    }
}
