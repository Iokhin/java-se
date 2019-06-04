package ru.iokhin.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.util.DateFormatter;
import ru.iokhin.tm.util.FieldConst;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    public ProjectRepository(Connection connection) {
        super(connection);
    }

    @Override
    @SneakyThrows
    public Project persist(@NotNull Project entity) {
        @NotNull final String query = "INSERT INTO project (id, description, name, dateBegin, user_id, status)" +
                " values (?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, entity.getId());
        statement.setString(2, entity.getDescription());
        statement.setString(3, entity.getName());
        statement.setString(4, DateFormatter.format(entity.getStartDate()));
        statement.setString(5, entity.getParentId());
        statement.setString(6, entity.getStatus().toString());
        statement.executeUpdate();
        statement.close();
        return entity;
    }

    @Override
    @SneakyThrows
    public Project merge(@NotNull Project entity) {
        @NotNull final String query = "UPDATE project SET description = ?, name = ?, dateBegin = ?, user_id = ?, status = ? where id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, entity.getDescription());
        statement.setString(2, entity.getName());
        statement.setString(3, DateFormatter.format(entity.getStartDate()));
        statement.setString(4, entity.getParentId());
        statement.setString(5, entity.getStatus().toString());
        statement.setString(6, entity.getId());
        statement.executeUpdate();
        statement.close();
        return entity;
    }

    @Nullable
    @SneakyThrows
    Project fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Project project = new Project();
        project.setId(row.getString(FieldConst.ID));
        project.setName(row.getString(FieldConst.NAME));
        project.setDescription(row.getString(FieldConst.DESCRIPTION));
        project.setStartDate(row.getDate(FieldConst.DATE_BEGIN));
        project.setEndDate(row.getDate(FieldConst.DATE_END));
        project.setParentId(row.getString(FieldConst.USER_ID));
        project.setStatusFromRepository(row.getString(FieldConst.STATUS));
        return project;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM project where user_id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> projectList = new ArrayList<>(0);
        while (resultSet.next()) projectList.add(fetch(resultSet));
        statement.close();
        return projectList;
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "DELETE FROM project WHERE user_id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public Project findOneByUserId(@NotNull final String userId, @NotNull final String id) {
        @NotNull final String query = "SELECT * FROM project WHERE user_id = ? and id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, id);
        Project project = null;
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) project = fetch(resultSet);
        return project;
    }

    @Override
    @SneakyThrows
    public Project remove(@NotNull final String parentId, @NotNull final String id) {
        @Nullable final Project project = findOneByUserId(parentId, id);
        if (project == null) return null;
        @NotNull final String query = "DELETE FROM project WHERE id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
        return project;
    }

    @Override
    public Collection<Project> sortByUserId(@NotNull final String userId, @NotNull final Comparator<Project> comparator) {
        List<Project> projectList = new ArrayList<>(findAllByUserId(userId));
        projectList.sort(comparator);
        return projectList;
    }

    @Override
    public Collection<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        List<Project> projectList = new ArrayList<>(0);
        for (Project project : findAllByUserId(userId)) {
            if (project.getName().toLowerCase().contains(keyWord.toLowerCase()) ||
                    project.getDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                projectList.add(project);
            }
        }
        return projectList;
    }

    @Override
    @SneakyThrows
    public Collection<Project> findAll() {
        @NotNull final String query = "SELECT * FROM project";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> projectList = new ArrayList<>(0);
        while (resultSet.next()) projectList.add(fetch(resultSet));
        statement.close();
        return projectList;
    }

    public Project getOne(@NotNull String id) throws SQLException {
        @NotNull final String query = "SELECT * FROM project WHERE id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        Project project = fetch(resultSet);
        statement.close();
        return project;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
