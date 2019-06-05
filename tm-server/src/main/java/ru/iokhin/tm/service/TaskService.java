package ru.iokhin.tm.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.exeption.ObjectNotFound;
import ru.iokhin.tm.util.ComparatorUtil;
import ru.iokhin.tm.util.StringValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class TaskService implements ITaskService {

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

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
        repository.merge(task);
        return task;
    }

    @Override
    @SneakyThrows
    public Task removeByUserId(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id);
        @Nullable final Task task = repository.findOneByUserId(userId, id);
        if (task == null) return null;
        return repository.remove(userId, id);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        repository.removeAllByUserId(userId);
    }

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
            return session.getMapper(ITaskRepository.class).findOneByUserId(id);
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
    public boolean removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull boolean flag = false;
        for (Task task : findAllByProjectId(userId, projectId)) {
            repository.remove(userId, task.getId());
            flag = true;
        }
        return flag;
    }

    @Override
    @SneakyThrows
    public Collection<Task> sortByUserId(@NotNull String userId, @NotNull String comparator) {
        StringValidator.validate(userId, comparator);
        if (comparator.equals("order")) return repository.findAllByUserId(userId);
        if (ComparatorUtil.getTaskComparator(comparator) == null) return null;
        return repository.sortByUserId(userId, ComparatorUtil.getTaskComparator(comparator));
    }

    @Override
    public List<Task> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        return repository.findByPartOfNameOrDescription(userId, keyWord);
    }

    @Override
    public Task persist(@NotNull Task entity) {
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
