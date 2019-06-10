package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    public TaskRepository(@NotNull EntityManager em) {
        super(em);
    }

    @Override
    public Task findOneByUserId(@NotNull final User user, @NotNull final String id) {
        List<Task> tasks = em.createQuery("SELECT t FROM Task t WHERE t.user = :user AND t.id = :id", Task.class)
                .setParameter("user", user)
                .setParameter("id", id)
                .getResultList();
        if (tasks.size() == 0) return null;
        return tasks.get(0);
    }

    @Override
    public List<Task> findAll() {
        return em.createQuery("SELECT t FROM Task t", Task.class)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserId(@NotNull final User user) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user", Task.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Task> findAllByProjectId(@NotNull User user, @NotNull Project project) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user AND t.project = :project", Task.class)
                .setParameter("user", user)
                .setParameter("project", project)
                .getResultList();
    }

    @Override
    public void removeByUserId(@NotNull final User user, @NotNull final String id) {
        Task task = findOneByUserId(user, id);
        if (task == null) return;
        em.remove(task);
    }

    @Override
    public void removeAllByUserId(@NotNull final User user) {
        List<Task> tasks = findAllByUserId(user);
        if (tasks == null) return;
        tasks.forEach(em::remove);
    }

    @Override
    public void removeAllByProjectId(@NotNull final User user, @NotNull final Project project) {
        List<Task> tasks = findAllByProjectId(user, project);
        if (tasks == null) return;
        tasks.forEach(this::remove);
    }

    @Override
    public List<Task> sortByUserId(@NotNull final User user, @NotNull final String parameter) {
        @NotNull final CriteriaBuilder cb = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        @NotNull final Root<Task> taskRoot = cq.from(Task.class);
        @NotNull final Predicate condition = cb.equal(taskRoot.get("user"), user);
        cb.conjunction();
        cq.select(taskRoot).where(condition);
        cq.orderBy(cb.desc(taskRoot.get(parameter)));
        @NotNull final TypedQuery<Task> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Task> findByPartOfNameOrDescription(@NotNull final User user, @NotNull final String keyWord) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user AND " +
                "(t.name LIKE :part OR t.description LIKE :part)", Task.class)
                .setParameter("user", user)
                .setParameter("part", "%" + keyWord + "%")
                .getResultList();
    }
}
