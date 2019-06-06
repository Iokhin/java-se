package ru.iokhin.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.User;

import java.sql.SQLException;
import java.util.Collection;

public interface IUserRepository {

    String INSERT = "INSERT IGNORE INTO user (id, login, passwordHash, role) VALUES(#{id}, #{login}, #{passwordHash}, #{roleType})";
    String SELECT_BY_ID = "SELECT * FROM user WHERE id = #{id}";
    String SELECT_ALL = "SELECT * FROM user";
    String DELETE_BY_ID = "DELETE FROM user WHERE id = #{id}";
    String DELETE_ALL = "DELETE FROM user";
    String UPDATE_BY_ID = "UPDATE user SET login = #{login}, passwordHash = #{passwordHash}, role = #{roleType} WHERE id = #{id}";
    String SELECT_BY_LOGIN = "SELECT * FROM user WHERE login = #{login}";

    @Select(SELECT_BY_LOGIN)
    @Results(value = {
           @Result(property = "id", column = "id"),
           @Result(property = "login", column = "login"),
           @Result(property = "passwordHash", column = "passwordHash"),
           @Result(property = "roleType", column = "role")
    })
    User findByLogin(@NotNull final String login);

    @Select(SELECT_BY_ID)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "passwordHash", column = "passwordHash"),
            @Result(property = "roleType", column = "role")
    })
    User findOne(final @NotNull String id);

    @Select(SELECT_ALL)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "login", column = "login"),
            @Result(property = "passwordHash", column = "passwordHash"),
            @Result(property = "roleType", column = "role")
    })
    Collection<User> findAll() throws SQLException;

    @Delete(DELETE_BY_ID)
    User remove(final @NotNull String id) throws SQLException;

    @Delete(DELETE_ALL)
    void removeAll();

    @Update(UPDATE_BY_ID)
    void merge(final @NotNull User user);

    @Insert(INSERT)
    void persist(final @NotNull User entity) throws SQLException;
}
