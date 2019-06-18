package ru.iokhin.tm;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.iokhin.tm.config.Bootstrap;
import ru.iokhin.tm.config.SpringConfig;

public final class Application {
    public static void main(String[] args) {
        @NotNull final ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        @NotNull final Bootstrap bootstrap = context.getBean(Bootstrap.class);
        bootstrap.init();
    }
}
