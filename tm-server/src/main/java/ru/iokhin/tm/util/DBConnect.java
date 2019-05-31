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

}
