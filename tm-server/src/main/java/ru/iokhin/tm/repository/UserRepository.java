package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.api.repository.IUserRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(@NotNull EntityManager em) {
        super(em);
    }

    @Override
    public User findByLogin(@NotNull String login) {
        @NotNull final List<User> users = em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                .setParameter("login", login)
                .getResultList();
        if (users.size() == 0) return null;
        return users.get(0);
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
