package ru.iokhin.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.util.DateFormatter;
import ru.iokhin.tm.util.FieldConst;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    public TaskRepository(Connection connection) {
        super(connection);
    }

    @Override
    @SneakyThrows
    Task fetch(@Nullable ResultSet resultSet) {
        if (resultSet == null) return null;
        @NotNull final Task task = new Task();
        task.setId(resultSet.getString(FieldConst.ID));
        task.setName(resultSet.getString(FieldConst.NAME));
        task.setDescription(resultSet.getString(FieldConst.DESCRIPTION));
        task.setStatus(resultSet.getString(FieldConst.STATUS));
        return task;
    }

    @Override
    public Task persist(@NotNull Task entity) throws SQLException {
        @NotNull final String query = "INSERT INTO task (id, description, name, dateBegin, user_id, status)" +
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
    public Task merge(@NotNull Task entity) {
        @NotNull final String query = "UPDATE task SET description = ?, name = ?, dateBegin = ?," +
                " user_id = ?, status = ? where id = ?";
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

    @Override
    @SneakyThrows
    public Collection<Task> findAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "SELECT * FROM project where user_id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> taskList = new ArrayList<>(0);
        while (resultSet.next()) taskList.add(fetch(resultSet));
        statement.close();
        return taskList;
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NotNull final String userId) {
        @NotNull final String query = "DELETE FROM task WHERE user_id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public Task findOneByUserId(@NotNull final String userId, @NotNull final String id) {
        @NotNull final String query = "SELECT FROM task WHERE user_id = ? and id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        Task task = null;
        if (resultSet.next()) task = fetch(resultSet);
        statement.close();
        return task;
    }

    @Override
    @SneakyThrows
    public Task remove(@NotNull final String parentId, @NotNull final String id) {
        @Nullable final Task task = findOneByUserId(parentId, id);
        if (task == null) return null;
        @NotNull final String query = "DELETE FROM project WHERE id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
        return task;
    }

    @Override
    public Collection<Task> sortByUserId(@NotNull final String userId, @NotNull final Comparator<Task> comparator) {
        List<Task> taskList = new ArrayList<>(findAllByUserId(userId));
        taskList.sort(comparator);
        return taskList;
    }

    @Override
    public List<Task> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord) {
        List<Task> taskList = new ArrayList<>(0);
        for (Task task : findAllByUserId(userId)) {
            if (task.getName().toLowerCase().contains(keyWord.toLowerCase()) ||
                    task.getDescription().toLowerCase().contains(keyWord.toLowerCase()))
                taskList.add(task);
        }
        return taskList;
    }

    @Override
    @SneakyThrows
    public Collection<Task> findAll() {
        @NotNull final String query = "SELECT * FROM task";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> taskList = new ArrayList<>(0);
        while (resultSet.next()) taskList.add(fetch(resultSet));
        statement.close();
        return taskList;
    }

    @Override
    public Task findOne(@NotNull String id) throws SQLException {
        @NotNull final String query = "SELECT * FROM task WHERE id = ?";
        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) return null;
        Task task = fetch(resultSet);
        statement.close();
        return task;
    }
}
