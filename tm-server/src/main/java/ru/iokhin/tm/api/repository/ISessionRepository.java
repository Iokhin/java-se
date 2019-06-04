package ru.iokhin.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface ISessionRepository {

    String INSERT = "INSERT INTO session (id, signature, timestamp, user_id) VALUES (#{id}, #{signature}, #{timeStamp}, #{parentId})";
    String UPDATE = "UPDATE session SET id = #{id}, signature = #{signature}, timestamp = #{timeStamp}, user_id = #{parentId}";
    String SELECT_BY_ID = "SELECT * FROM session WHERE id = #{id}";
    String SELECT_ALL = "SELECT * FROM session";
    String SELECT_ALL_BY_UserID = "SELECT * FROM session WHERE user_id = #{parentId}";
    String REMOVE_BY_ID = "DELETE FROM session WHERE id = #{id}";
    String REMOVE_ALL = "DELETE * FROM session";

    @Insert(INSERT)
    void persist(final @NotNull Session entity);

    @Update(UPDATE)
    void merge(final @NotNull Session entity);

    @Select(SELECT_ALL)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "signature", column = "signature"),
            @Result(property = "timeStamp", column = "timestamp"),
            @Result(property = "parentId", column = "user_id")
    })
    Collection<Session> findAll();

    @Select(SELECT_ALL_BY_UserID)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "signature", column = "signature"),
            @Result(property = "timeStamp", column = "timestamp"),
            @Result(property = "parentId", column = "user_id")
    })
    Collection<Session> findAllByUserId(@NotNull final String userId);

    @Select(SELECT_BY_ID)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "signature", column = "signature"),
            @Result(property = "timeStamp", column = "timestamp"),
            @Result(property = "parentId", column = "user_id")
    })
    Session findOne(final @NotNull String id);

    @Delete(REMOVE_BY_ID)
    void remove(final @NotNull String id);

    @Delete(REMOVE_ALL)
    void removeAll();

}
