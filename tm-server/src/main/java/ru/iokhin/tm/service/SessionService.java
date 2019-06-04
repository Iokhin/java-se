package ru.iokhin.tm.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.PropertiesUtil;
import ru.iokhin.tm.util.SignatureUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

//@NoArgsConstructor
@RequiredArgsConstructor
public class SessionService implements ISessionService {

    @NotNull
    private static final PropertiesUtil propertiesUtil = new PropertiesUtil();
    @NotNull
    private static final int cycle = Integer.parseInt(propertiesUtil.getCycle());
    @NotNull
    private static final String salt = propertiesUtil.getSalt();
    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    @SneakyThrows
    public Session create(@NotNull String userId) {
        @NotNull final Session session = new Session();
        session.setTimeStamp(new Date());
        session.setParentId(userId);
        sign(session);
        return persist(session);
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
        System.out.println(sign(temp));
        System.out.println(session.getSignature());
        if (!sign(temp).equals(session.getSignature())) throw new AuthException("INVALID SESSION");
    }

    @Override
    public String sign(@NotNull Session session) {
        session.setSignature(null);
        session.setSignature(SignatureUtil.sign(session, salt, cycle));
        return session.getSignature();
    }

    @Override
    public Session persist(@NotNull Session entity) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ISessionRepository.class).persist(entity);
            session.commit();
            return entity;
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Session merge(@NotNull Session entity) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ISessionRepository.class).merge(entity);
            session.commit();
            return entity;
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    @SneakyThrows
    public Session findOne(@NotNull String id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ISessionRepository.class).findOne(id);
        }
    }

    @Override
    public Collection<Session> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ISessionRepository.class).findAll();
        }
    }

    @Override
    public Session remove(@NotNull String id) {
        Session session = findOne(id);
        if (session == null) return null;
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.getMapper(ISessionRepository.class).remove(session.getId());
            sqlSession.commit();
            return session;
        } catch (Exception e) {
            if (sqlSession != null) sqlSession.rollback();
            throw e;
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public void removeAll() {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ISessionRepository.class).removeAll();
            session.commit();
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }
}
