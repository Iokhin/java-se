package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.exeption.ObjectNotFound;
import ru.iokhin.tm.util.StringValidator;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskService extends AbstractService<Task> implements ITaskService {

    public TaskService(@NotNull EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public Task add(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name) {
        StringValidator.validate(projectId, name, userId);
        return persist(new Task(userId, projectId, name));
    }

    @Override
    @SneakyThrows
    public Task edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id, userId);
        @Nullable final Task task = findOneByUserId(userId, id);
        if (task == null) return null;
        task.setName(name);
        merge(task);
        return task;
    }

    @Override
    @SneakyThrows
    public Task removeByUserId(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id);
        Task entity = findOneByUserId(userId, id);
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ITaskRepository.class).removeByUserId(userId, id);
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
    public void removeAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ITaskRepository.class).removeAllByUserId(userId);
            session.commit();
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }    }

    @Override
    public Task findOneByUserId(@NotNull String userId, @NotNull String id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ITaskRepository.class).findOneByUserId(userId, id);
        }
    }

    @Override
    @SneakyThrows
    public Collection<Task> findAllByUserId(@NotNull final String userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ITaskRepository.class).findAllByUserId(userId);
        }
    }

    @Override
    public Collection<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final Collection<Task> tasks = new ArrayList<>(0);
        for (Task task : findAllByUserId(userId)) {
            if (task.getProjectId().equals(projectId))
                tasks.add(task);
        }
        return tasks;
    }

    @Override
    public void removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ITaskRepository.class).removeAllByProjectId(userId, projectId);
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
    public Collection<Task> sortByUserId(@NotNull String userId, @NotNull String parameter) {
        StringValidator.validate(userId, parameter);
        if ("order".equals(parameter)) return findAllByUserId(userId);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ITaskRepository.class).sortByUserId(userId, parameter);
        }
    }

    @Override
    public List<Task> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ITaskRepository.class).findByPartOfNameOrDescription(userId, keyWord);
        }
    }

    @Override
    public void persist(@NotNull Task entity) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ITaskRepository.class).persist(entity);
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
    public Task merge(@NotNull Task entity) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ITaskRepository.class).merge(entity);
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
    public Task findOne(@NotNull String id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ITaskRepository.class).findOne(id);
        }
    }

    @Override
    public Collection<Task> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ITaskRepository.class).findAll();
        }
    }

    @Override
    @SneakyThrows
    public Task remove(@NotNull String id) {
        Task task = findOne(id);
        if (task == null) throw new ObjectNotFound();
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            session.getMapper(ITaskRepository.class).remove(id);
            session.commit();
            return task;
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
            session.getMapper(ITaskRepository.class).removeAll();
            session.commit();
        } catch (Exception e) {
            if (session != null) session.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }
}
