package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.entity.User;

@Repository("userRepository")
@Scope("singleton")
public interface IUserRepository extends JpaRepository<User, String> {

    User findAnyByLogin(@NotNull final String login);

}
