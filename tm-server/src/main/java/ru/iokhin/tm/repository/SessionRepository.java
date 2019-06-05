//package ru.iokhin.tm.repository;
//
//import lombok.SneakyThrows;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import ru.iokhin.tm.api.repository.ISessionRepository;
//import ru.iokhin.tm.entity.Session;
//import ru.iokhin.tm.entity.User;
//import ru.iokhin.tm.util.DateFormatter;
//import ru.iokhin.tm.util.FieldConst;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {
//    @Override
//    @SneakyThrows
//    Session fetch(@Nullable ResultSet resultSet) {
//        if (resultSet == null) return null;
//        @NotNull final Session session = new Session();
//        session.setId(resultSet.getString(FieldConst.ID));
//        session.setParentId(resultSet.getString(FieldConst.USER_ID));
//        session.setTimeStamp(resultSet.getDate(FieldConst.TIMESTAMP));
//        session.setSignature(resultSet.getString(FieldConst.SIGNATURE));
//        return session;
//    }
//
//    @Override
////    @SneakyThrows
//    public Session persist(@NotNull Session entity) throws SQLException {
//        @NotNull final String query = "INSERT INTO session (id, user_id, timestamp, signature)" +
//                " values (?, ?, ?, ?)";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        statement.setString(1, entity.getId());
//        statement.setString(2, entity.getParentId());
//        statement.setString(3, DateFormatter.format(entity.getTimeStamp()));
//        statement.setString(4, entity.getSignature());
//        statement.executeUpdate();
//        statement.close();
//        return entity;
//    }
//
//    @Override
//    public Collection<Session> findAll() throws SQLException {
//        @NotNull final String query = "SELECT * FROM session";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        @NotNull final ResultSet resultSet = statement.executeQuery();
//        @NotNull final List<Session> sessionList = new ArrayList<>();
//        while (resultSet.next()) sessionList.add(fetch(resultSet));
//        statement.close();
//        return sessionList;
//    }
//
//    public SessionRepository(Connection connection) {
//        super(connection);
//    }
//
//    @Override
//    @SneakyThrows
//    public Collection<Session> findAllByUserId(@NotNull String userId) {
//        List<Session> sessionArrayList = new ArrayList<>(0);
//        for (Session session : findAll()) {
//            if (session.getParentId().equals(userId))
//                sessionArrayList.add(session);
//        }
//        return sessionArrayList;
//    }
//
//    @Override
//    public Session findOneByUserId(@NotNull String id) throws SQLException {
//        @NotNull final String query = "SELECT * FROM session WHERE id = ?";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        statement.setString(1, id);
//        @NotNull final ResultSet resultSet = statement.executeQuery();
//        if (!resultSet.next()) return null;
//        Session session = fetch(resultSet);
//        statement.close();
//        return session;
//    }
//
//    @Override
//    public Session removeByUserId(@NotNull String id) throws SQLException {
//        @NotNull final String query = "DELETE FROM session WHERE id = ?";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        @NotNull final Session session = findOneByUserId(id);
//        statement.setString(1, id);
//        statement.executeUpdate();
//        statement.close();
//        return session;
//    }
//
//    @Override
//    @SneakyThrows
//    public void removeAll() {
//        @NotNull final String query = "DELETE FROM session";
//        @NotNull final PreparedStatement statement = getConnection().prepareStatement(query);
//        statement.executeUpdate();
//        statement.close();
//    }
//}
