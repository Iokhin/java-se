package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(@NotNull EntityManager em) {
        super(em);
    }

    @Override
    public User findByLogin(@NotNull String login) {
        return em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void removeAll() {
        findAll().forEach(this::remove);
    }
}
