package ru.iokhin.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.util.DateFormatter;
import ru.iokhin.tm.util.FieldConst;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//public class UserRepository implements IUserRepository {

//    @SneakyThrows
//    User fetch(@Nullable ResultSet resultSet) {
//        if (resultSet == null) return null;
//        @NotNull final User user = new User();
//        user.setId(resultSet.getString(FieldConst.ID));
//        user.setLogin(resultSet.getString(FieldConst.LOGIN));
//        user.setPasswordHash(resultSet.getString(FieldConst.PASSWORD));
//        user.setRole(resultSet.getString(FieldConst.ROLE));
//        return user;
//    }
//
//    @Override
//    public void persist(@NotNull User entity) throws SQLException {
//        @NotNull final String query = "INSERT IGNORE INTO user (id, login, passwordHash, role)" +
//                " values (?, ?, ?, ?)";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        statement.setString(1, entity.getId());
//        statement.setString(2, entity.getLogin());
//        statement.setString(3, entity.getPasswordHash());
//        statement.setString(4, entity.getRoleType().toString());
//        statement.executeUpdate();
//        statement.close();
////        return entity;
//    }
//
//    @Override
//    @SneakyThrows
//    public User merge(@NotNull User entity) {
//        @NotNull final String query = "UPDATE user SET description = ?, name = ?, dateBegin = ?," +
//                " user_id = ?, role = ? where id = ?";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        statement.setString(1, entity.getDescription());
//        statement.setString(2, entity.getName());
//        statement.setString(3, DateFormatter.format(entity.getStartDate()));
//        statement.setString(4, entity.getParentId());
//        statement.setString(5, entity.getRoleType().toString());
//        statement.setString(6, entity.getId());
//        statement.executeUpdate();
//        statement.close();
//        return entity;
//    }
//
//    @Override
//    public User findByLogin(@NotNull String login) {
//        for (User user : findAll()) {
//            if (user.getLogin().equals(login))
//                return user;
//        }
//        return null;
//    }
//
//    @Override
//    @SneakyThrows
//    public Collection<User> findAll() {
//        @NotNull final String query = "SELECT * FROM user";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        @NotNull final ResultSet resultSet = statement.executeQuery();
//        @NotNull final List<User> userList = new ArrayList<>();
//        while (resultSet.next())
//            userList.add(fetch(resultSet));
//        statement.close();
//        return userList;
//    }
//
//    @Override
//    @SneakyThrows
//    public User findOneByUserId(@NotNull String id) {
//        @NotNull final String query = "SELECT * FROM user WHERE id = ?";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        statement.setString(1, id);
//        @NotNull final ResultSet resultSet = statement.executeQuery();
//        if (!resultSet.next()) return null;
//        User user = fetch(resultSet);
//        statement.close();
//        return user;
//    }
//
//    @Override
//    @SneakyThrows
//    public User removeByUserId(@NotNull String id) {
//        @NotNull final String query = "DELETE FROM user WHERE id = ?";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        @NotNull final User user = findOneByUserId(id);
//        statement.setString(1, id);
//        statement.executeUpdate();
//        statement.close();
//        return user;
//    }
//
//    @Override
//    @SneakyThrows
//    public void removeAll() {
//        @NotNull final String query = "DELETE FROM user";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        statement.executeUpdate();
//        statement.close();
//    }
//}
