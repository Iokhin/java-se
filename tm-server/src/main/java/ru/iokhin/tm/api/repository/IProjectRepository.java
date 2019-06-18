package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;

import java.util.List;

@Repository("projectRepository")
@Scope("singleton")
public interface IProjectRepository extends JpaRepository<Project, String> {

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
