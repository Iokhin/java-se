package ru.iokhin.tm;

import org.jetbrains.annotations.NotNull;

public final class Application {
    public static void main(@NotNull String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
