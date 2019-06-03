package ru.iokhin.tm.service;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.Application;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.PropertiesUtil;
import ru.iokhin.tm.util.SignatureUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@NoArgsConstructor
public class SessionService extends AbstractService<Session, ISessionRepository> implements ISessionService {

    @NotNull
    private static final int cycle = Integer.parseInt(PropertiesUtil.getProperties(Application.class).getProperty("cycle"));
    @NotNull
    private static final String salt = PropertiesUtil.getProperties(Application.class).getProperty("salt");

    public SessionService(ISessionRepository repository) {
        super(repository);
    }

    @Override
    @SneakyThrows
    public Session create(@NotNull String userId) {
        @NotNull final Session session = new Session();
        session.setTimeStamp(new Date());
        session.setParentId(userId);
        sign(session);
        return repository.persist(session);
    }

    @Override
    @SneakyThrows
    public void validate(@Nullable Session session) {
        if (session == null) throw new AuthException();
        if (session.getSignature() == null) throw new AuthException();
        if (session.getParentId() == null) throw new AuthException();
        if (session.getTimeStamp() == null) throw new AuthException();
        if (findOne(session.getId()) == null) throw new AuthException();
//        if (!session.getSignature().equals(findProject(session.getId()).getSignature()))
//            throw new AuthException("INVALID SESSION");
        final Session temp = session.clone();
        temp.setSignature(null);
        if (!sign(temp).equals(session.getSignature())) throw new AuthException("INVALID SESSION");
    }

    @Override
    public String sign(@NotNull Session session) {
        session.setSignature(null);
        session.setSignature(SignatureUtil.sign(session, salt, cycle));
        return session.getSignature();
    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    @SneakyThrows
    public Session findOne(@NotNull String id) {
        return repository.findOne(id);
    }
}
