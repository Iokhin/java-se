package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.repository.SessionRepository;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.util.PropertiesUtil;
import ru.iokhin.tm.util.SignatureUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;

public class SessionService extends AbstractService<SessionDTO> implements ISessionService {

    @NotNull
    private static final PropertiesUtil PROPERTIES_UTIL = new PropertiesUtil();
    @NotNull
    private static final int CYCLE = Integer.parseInt(PROPERTIES_UTIL.getCycle());
    @NotNull
    private static final String SALT = PROPERTIES_UTIL.getSalt();

    public SessionService(@NotNull EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    @SneakyThrows
    public SessionDTO create(@NotNull String userId) {
        @NotNull final SessionDTO session = new SessionDTO();
        session.setTimeStamp(new Date());
        session.setParentId(userId);
        sign(session);
        merge(session);
        return session;
    }

    @Override
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
    public String sign(@NotNull SessionDTO session) {
        session.setSignature(null);
        session.setSignature(SignatureUtil.sign(session, SALT, CYCLE));
        return session.getSignature();
    }

    @Override
    public Session getSessionFromDTO(SessionDTO sessionDTO, EntityManager em) {
        Session session = new Session();
        session.setId(sessionDTO.getId());
        session.setUser(getUser(sessionDTO, em));
        session.setSignature(sessionDTO.getSignature());
        session.setTimeStamp(sessionDTO.getTimeStamp());
        return session;
    }

    @Override
    public User getUser(SessionDTO sessionDTO, EntityManager em) {
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @NotNull final String id = sessionDTO.getParentId();
        @NotNull final User user = userRepository.findOne(id);
        return user;
    }

    @Override
    public void persist(@NotNull SessionDTO entity) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        @NotNull final Session session = getSessionFromDTO(entity, em);
        try {
            em.getTransaction().begin();
            sessionRepository.persist(session);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull SessionDTO entity) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        @NotNull final Session session = getSessionFromDTO(entity, em);
        try {
            em.getTransaction().begin();
            sessionRepository.merge(session);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public SessionDTO findOne(@NotNull String id) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        @NotNull final Session session = sessionRepository.findOne(id);
        if (session == null) return null;
        return session.getSessionDTO();
    }

    @Override
    public void remove(@NotNull SessionDTO entity) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(em);
        @NotNull final Session session = getSessionFromDTO(entity, em);
        try {
            em.getTransaction().begin();
            sessionRepository.remove(session);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
