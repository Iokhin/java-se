package ru.iokhin.tm.util;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Application;

import java.sql.*;
import java.util.Properties;

public class DBConnect {

    @SneakyThrows
    static public Connection connect(@NotNull final Properties properties){
        @NotNull final String url = properties.getProperty("url");
        @NotNull final String login = properties.getProperty("login");
        @NotNull final String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

//    public static void main(String[] args) throws SQLException {
//        ResultSet resultSet = connect(PropertiesUtil.getProperties(Application.class)).createStatement().executeQuery("SELECT * from project");
//        while (resultSet.next())
//            System.out.println(resultSet.getString(FieldConst.ID));
//    }
}
