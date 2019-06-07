package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.util.StringValidator;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;

public class ProjectService extends AbstractService<Project> implements IProjectService {

    public ProjectService(@NotNull EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public Project add(@NotNull final String userId, @NotNull final String name) {
        StringValidator.validate(name);
        return persist(new Project(userId, name));
    }

    @Override
    public Project edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id, userId);
        @Nullable final Project project = findOneByUserId(userId, id);
        if (project == null) return null;
        project.setName(name);
        merge(project);
        return project;
    }

    @Override
    public Project edit(@NotNull String userId, @NotNull String id, @NotNull String name, @NotNull Status status) {
        StringValidator.validate(name, id, userId);
        @Nullable final Project project = findOneByUserId(userId, id);
        if (project == null) return null;
        project.setName(name);
        project.setStatus(status);
        merge(project);
        return project;
    }

    @Override
    public Project remove(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id, userId);
        @Nullable final Project project = findOneByUserId(userId, id);
        if (project == null) return null;
        return remove(id);
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(IProjectRepository.class).removeAllByUserId(userId);
            session.commit();
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    @SneakyThrows
    public Collection<Project> findAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).findAllByUserId(userId);
        }
    }

    @Override
    public Project findOneByUserId(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(userId, id);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).findOneByUserId(userId, id);
        }
    }

    @Override
    @SneakyThrows
    public Collection<Project> sortByUserId(@NotNull final String userId, @NotNull final String parameter) {
        StringValidator.validate(userId, parameter);
        if ("order".equals(parameter)) return findAllByUserId(userId);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).sortByUserId(userId, parameter);
        }
    }

    @Override
    @SneakyThrows
    public Collection<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).findByPartOfNameOrDescription(userId, part);
        }
    }

    @Override
    public void persist(@NotNull Project entity) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(IProjectRepository.class).persist(entity);
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
    public Project merge(@NotNull Project entity) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(IProjectRepository.class).merge(entity);
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
    public Project findOne(@NotNull String id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).findOne(id);
        }
    }

    @Override
    public Collection<Project> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(IProjectRepository.class).findAll();
        }
    }

    @Override
    public Project remove(@NotNull String id) {
        Project project = findOne(id);
        if (project == null) return null;
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(IProjectRepository.class).remove(id);
            session.commit();
            return project;
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void removeAll() {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(IProjectRepository.class).removeAll();
            session.commit();
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }
}
