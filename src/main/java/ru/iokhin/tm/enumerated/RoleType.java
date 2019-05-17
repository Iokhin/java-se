package ru.iokhin.tm.enumerated;

import org.jetbrains.annotations.NotNull;

public enum RoleType {
    ADMIN("Администратор"),
    USER("Обычный пользователь");

    @NotNull
    private final String title;

    RoleType(@NotNull String title) {
        this.title = title;
    }

    public String displayName() {
        return this.title;
    }

}
