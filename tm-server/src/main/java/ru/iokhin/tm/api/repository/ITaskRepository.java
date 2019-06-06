package ru.iokhin.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ITaskRepository {

    String INSERT = "INSERT INTO task (id, dateBegin, dateEnd, name, description, user_id, status, project_id) " +
            "VALUES (#{id}, #{startDate}, #{endDate}, #{name}, #{description}, #{parentId}, #{status}, #{projectId})";
    String MERGE = "UPDATE task SET dateBegin = #{startDate}, dateEnd = #{endDate}, name = #{name}, " +
            "description = #{description}, user_id = #{parentId}, status = #{status}, project_id = #{projectId} WHERE id = #{id}";
    String SELECT_BY_ID = "SELECT * FROM task WHERE id = #{id}";
    String SELECT_BY_UserID = "SELECT * FROM task WHERE user_id = #{userId} AND id = #{id}";
    String SELECT_ALL = "SELECT * FROM task";
    String SELECT_ALL_BY_UserID = "SELECT * FROM task WHERE user_id = #{parentId}";
    String REMOVE_BY_ID = "DELETE FROM task WHERE id = #{id}";
    String REMOVE_BY_UserID = "DELETE FROM task WHERE id = #{id} AND user_id = #{userId}";
    String REMOVE_ALL = "DELETE * FROM task";
    String REMOVE_ALL_BY_UserID = "DELETE * FROM task WHERE user_id = #{userId}";
    String SORT = "SELECT * FROM task WHERE user_id = #{userId} ORDER BY #{parameter}";
    String REMOVE_ALL_BY_ProjectID = "DELETE * FROM task WHERE user_id = #{userId} and project_id = #{projectId}";

    @Insert(INSERT)
    Integer persist(final @NotNull Task entity);

    @Insert(MERGE)
    Integer merge(final @NotNull Task entity);

    @Select(SELECT_BY_ID)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "projectId", column = "project_id")
    })
    Task findOne(final @NotNull String id);

    @Select(SELECT_ALL)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "projectId", column = "project_id")
    })
    Collection<Task> findAll();

    @Delete(REMOVE_BY_ID)
    Integer remove(final @NotNull String id);

    @Delete(REMOVE_ALL)
    Integer removeAll();

    @Select(SELECT_ALL_BY_UserID)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "projectId", column = "project_id")
    })
    Collection<Task> findAllByUserId(@NotNull final String userId);

    @Delete(REMOVE_ALL_BY_UserID)
    Integer removeAllByUserId(@NotNull final String userId);

    @Select(SELECT_BY_UserID)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "projectId", column = "project_id")
    })
    Task findOneByUserId(@NotNull final String parentId, @NotNull final String id);

    @Delete(REMOVE_BY_UserID)
    Integer removeByUserId(@NotNull final String parentId, @NotNull final String id);

    @Select(SORT)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "projectId", column = "project_id")
    })
    Collection<Task> sortByUserId(@Param("userId")@NotNull final String userId,
                                  @Param("parameter")String parameter);

    List<Task> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord);

    @Delete(REMOVE_ALL_BY_ProjectID)
    void removeAllByProjectId(@Param("userId") String userId,
                              @Param("projectId") String projectId);

}
