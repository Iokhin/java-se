package ru.iokhin.tm.api.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import java.util.List;

@Repository
public interface ITaskRepository extends EntityRepository<Task, String> {

    Task findAnyByUserAndId(@NotNull User user, @NotNull String id);

    List<Task> findByUser(@NotNull User user);

    List<Task> findByUserAndProject(@NotNull User user, @NotNull Project project);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 ORDER BY e.status")
    List<Task> sortByStatus(@NotNull User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 ORDER BY e.dateStart")
    List<Task> sortByDateStart(@NotNull User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 ORDER BY e.dateEnd")
    List<Task> sortByDateEnd(@NotNull User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 AND (e.name LIKE CONCAT('%', ?2, '%') " +
            "OR e.description LIKE CONCAT('%', ?2, '%'))")
    List<Task> findByPartOfNameOrDescription(@NotNull User user, @NotNull String keyWord);

}
