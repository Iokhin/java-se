package ru.iokhin.tm.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties load(Class main) {
        @NotNull final Properties properties = new Properties();
        try (InputStream resourceStream = main.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
