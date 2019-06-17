package ru.iokhin.tm.api.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;

import java.util.List;

@Repository
public interface IProjectRepository extends EntityRepository<Project, String> {

    Project findAnyByUserAndId(@NotNull User user, @NotNull String id);

    List<Project> findByUser(@NotNull User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 ORDER BY e.status")
    List<Project> sortByStatus(@NotNull User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 ORDER BY e.dateStart")
    List<Project> sortByDateStart(@NotNull User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 ORDER BY e.dateEnd")
    List<Project> sortByDateEnd(@NotNull User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 AND (e.name LIKE CONCAT('%', ?2, '%') " +
            "OR e.description LIKE CONCAT('%', ?2, '%'))")
    List<Project> findByPartOfNameOrDescription(@NotNull User user, @NotNull String keyWord);
}
