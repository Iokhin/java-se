package ru.iokhin.tm.util;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class PropertiesUtil {

    Properties properties;

    public PropertiesUtil() {
        @NotNull final Properties properties = new Properties();
        try (InputStream resourceStream = Application.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = properties;
    }

    public String getJdbcUrl() {
        return properties.getProperty("url");
    }

    public String getJdbcUsername() {
        return properties.getProperty("login");
    }

    public String getJdbcPassword() {
        return properties.getProperty("password");
    }

    public String getJdbcDriver() {
        return properties.getProperty("driver");
    }

    public String getSalt() {
        return properties.getProperty("salt");
    }

    public String getCycle() {
        return properties.getProperty("cycle");
    }
}
