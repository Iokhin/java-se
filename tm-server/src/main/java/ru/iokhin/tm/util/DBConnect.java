package ru.iokhin.tm.util;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Properties;

public class DBConnect {

    PropertiesUtil propertiesUtil = new PropertiesUtil();

    @SneakyThrows
    static public Connection connect(@NotNull final Properties properties){
        @NotNull final String url = properties.getProperty("url");
        @NotNull final String login = properties.getProperty("login");
        @NotNull final String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    @SneakyThrows
    public Connection getConnection(){
        @NotNull final String url = propertiesUtil.getJdbcUrl();
        @NotNull final String login = propertiesUtil.getJdbcUsername();
        @NotNull final String password = propertiesUtil.getJdbcPassword();
        return DriverManager.getConnection(url, login, password);
    }
}
