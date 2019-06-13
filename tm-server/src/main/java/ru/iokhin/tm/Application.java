package ru.iokhin.tm;

import ru.iokhin.tm.config.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public final class Application {
    public static void main(String[] args) {
        SeContainerInitializer.newInstance().initialize().select(Bootstrap.class).get().init();
    }
}
