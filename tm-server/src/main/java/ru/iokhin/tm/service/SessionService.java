package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.PropertiesUtil;
import ru.iokhin.tm.util.SignatureUtil;

import java.util.Date;

@Service("sessionService")
@Scope("singleton")
public class SessionService extends AbstractService<SessionDTO> implements ISessionService {

    @NotNull
    private static final PropertiesUtil PROPERTIES_UTIL = new PropertiesUtil();
    @NotNull
    private static final int CYCLE = Integer.parseInt(PROPERTIES_UTIL.getCycle());
    @NotNull
    private static final String SALT = PROPERTIES_UTIL.getSalt();
    @NotNull
    private final ISessionRepository sessionRepository;
    @NotNull
    private final IUserRepository userRepository;

    @Autowired
    public SessionService(@NotNull ISessionRepository sessionRepository, @NotNull IUserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @SneakyThrows
    @Transactional
    public SessionDTO create(@NotNull String userId) {
        @NotNull final SessionDTO session = new SessionDTO();
        session.setTimeStamp(new Date());
        session.setParentId(userId);
        sign(session);
        merge(session);
        return session;
    }

    @Override
    @Transactional
    public void validate(@Nullable SessionDTO session) throws AuthException {
        if (session == null) throw new AuthException();
        if (session.getSignature() == null) throw new AuthException();
        if (session.getParentId() == null) throw new AuthException();
        if (session.getTimeStamp() == null) throw new AuthException();
        if (findOne(session.getId()) == null) throw new AuthException();
        final SessionDTO temp = session.clone();
        temp.setSignature(null);
        if (!sign(temp).equals(session.getSignature())) throw new AuthException("INVALID SESSION");
    }

    @Override
    @Transactional
    public String sign(@NotNull SessionDTO session) {
        session.setSignature(null);
        session.setSignature(SignatureUtil.sign(session, SALT, CYCLE));
        return session.getSignature();
    }

    @Override
    @Transactional
    public Session getSessionFromDTO(SessionDTO sessionDTO) {
        Session session = new Session();
        session.setId(sessionDTO.getId());
        session.setUser(getUser(sessionDTO));
        session.setSignature(sessionDTO.getSignature());
        session.setTimeStamp(sessionDTO.getTimeStamp());
        return session;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(SessionDTO sessionDTO) {
        @NotNull final String id = sessionDTO.getParentId();
        @Nullable final User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    @Transactional
    public SessionDTO findById(@NotNull String id) {
        return findOne(id);
    }

    @Override
    @Transactional
    public void removeById(@NotNull String id) {
        SessionDTO sessionDTO = findOne(id);
        if (sessionDTO == null) return;
        remove(sessionDTO);
    }

    @Override
    @Transactional
    public void persist(@NotNull SessionDTO entity) {
        @NotNull final Session session = getSessionFromDTO(entity);
        sessionRepository.save(session);
    }

    @Override
    @Transactional
    public void merge(@NotNull SessionDTO entity) {
        @NotNull final Session session = getSessionFromDTO(entity);
        sessionRepository.save(session);
    }

    @Override
    @Transactional
    public SessionDTO findOne(@NotNull String id) {
        @Nullable final Session session = sessionRepository.findById(id).orElse(null);
        if (session == null) return null;
        return session.getSessionDTO();
    }

    @Override
    @Transactional
    public void remove(@NotNull SessionDTO entity) {
        @Nullable final Session session = sessionRepository.findById(entity.getId()).orElse(null);
        if (session == null) return;
        sessionRepository.delete(session);
    }
}
