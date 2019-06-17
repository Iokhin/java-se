package ru.iokhin.tm.api.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.User;


@Repository(forEntity = User.class)
public interface IUserRepository extends EntityRepository<User, String> {

    User findAnyByLogin(@NotNull final String login);

}
