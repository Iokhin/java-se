package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    public ProjectRepository(@NotNull final EntityManager em) {
        super(em);
    }

    @Override
    public Project findOneByUserId(@NotNull final User user, @NotNull final String id) {
        return em.createQuery("SELECT p FROM Project p WHERE p.user = :user AND p.id = :id", Project.class)
                .setParameter("user", user)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Project> findAllByUserId(@NotNull final User user) {
        return em.createQuery("SELECT p FROM Project p WHERE p.user = :user", Project.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public void removeAllByUserId(@NotNull final User user) {
        em.createQuery("DELETE p FROM Project p WHERE p.user = :user").setParameter("user", user).executeUpdate();
    }

    @Override
    public void removeByUserId(@NotNull final User user, @NotNull final String id) {
        em.createQuery("DELETE p FROM Project p WHERE p.user = :userId and p.id = :id")
                .setParameter("user", user).executeUpdate();
    }

    @Override
    public List<Project> sortByUserId(@NotNull final User user, @NotNull final String parameter) {
        @NotNull final CriteriaBuilder cb = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        @NotNull final Root<Project> projectRoot = cq.from(Project.class);
        @NotNull final Predicate condition = cb.equal(projectRoot.get("user"), user);
        cb.conjunction();
        cq.select(projectRoot).where(condition);
        cq.orderBy(cb.desc(projectRoot.get(parameter)));
        @NotNull final TypedQuery<Project> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Project> findByPartOfNameOrDescription(@NotNull final User user, @NotNull final String part) {
        return em.createQuery("SELECT p FROM Project p WHERE p.user = :user AND " +
                "(p.name LIKE :part OR p.description LIKE :part)", Project.class)
                .setParameter("user", user)
                .setParameter("part", "%" + part + "%")
                .getResultList();
    }
}
