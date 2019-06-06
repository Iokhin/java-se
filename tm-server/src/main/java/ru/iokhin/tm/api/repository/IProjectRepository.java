package ru.iokhin.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.Project;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectRepository {

    String INSERT = "INSERT INTO project (id, dateBegin, dateEnd, name, description, user_id, status) " +
            "VALUES (#{id}, #{startDate}, #{endDate}, #{name}, #{description}, #{parentId}, #{status})";
    String UPDATE = "UPDATE project SET dateBegin = #{startDate}, dateEnd = #{endDate}, " +
            "name = #{name}, description = #{description}, user_id = #{parentId}, status = #{status} WHERE id = #{id}";
    String SELECT_BY_ID = "SELECT * FROM project WHERE id = #{id}";
    String SELECT_BY_UserID = "SELECT * FROM project WHERE user_id = #{userId} AND id = #{id}";
    String SELECT_ALL = "SELECT * FROM project";
    String SELECT_ALL_BY_UserID = "SELECT * FROM project WHERE user_id = #{parentId}";
    String REMOVE_BY_ID = "DELETE FROM project WHERE id = #{id}";
    String REMOVE_BY_UserID = "DELETE FROM project WHERE id = #{id} AND user_id = #{userId}";
    String REMOVE_ALL = "DELETE * FROM project";
    String REMOVE_ALL_BY_UserID = "DELETE * FROM project WHERE user_id = #{userId}";
    String SORT = "SELECT * FROM project WHERE user_id = #{userId} ORDER BY #{parameter}";

    @Insert(INSERT)
    Integer persist(final @NotNull Project entity);

    @Update(UPDATE)
    Integer merge(final @NotNull Project entity);

    @Select(SELECT_BY_ID)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status")
    })
    Project findOne(final @NotNull String id);

    @Select(SELECT_BY_UserID)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status")
    })
    Project findOneByUserId(@Param("userId")@NotNull final String userId,
                            @Param("id")@NotNull final String id);

    @Select(SELECT_ALL)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status")
    })
    Collection<Project> findAll();

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
            @Result(property = "status", column = "status")
    })
    Collection<Project> findAllByUserId(@NotNull final String parentId);

    @Delete(REMOVE_ALL_BY_UserID)
    Integer removeAllByUserId(@NotNull final String userId);

    @Delete(REMOVE_BY_UserID)
    Integer removeByUserId(@Param("parentId")@NotNull final String parentId,
                           @Param("id")@NotNull final String id);

    @Select(SORT)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status")
    })
    Collection<Project> sortByUserId(@Param("userId") String userId,
                                     @Param("parameter") String parameter);

    @Select("SELECT * FROM project WHERE (name LIKE CONCAT('%', #{part}, '%') or description LIKE CONCAT('%', #{part}, '%')) and user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "parentId", column = "user_id"),
            @Result(property = "status", column = "status")
    })
    Collection<Project> findByPartOfNameOrDescription(@Param("userId") @NotNull final String userId,
                                                      @Param("part") @NotNull final String part);
}
