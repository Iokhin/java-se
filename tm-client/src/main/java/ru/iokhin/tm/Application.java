package ru.iokhin.tm;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.config.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public class Application {

    public static void main(@NotNull String[] args) {
        SeContainerInitializer.newInstance().initialize().select(Bootstrap.class).get().init();
    }

}
