package ru.iokhin.tm.enumerated;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public enum RoleType {
    ADMIN("Администратор"),
    USER("Обычный пользователь");

    @NotNull
    private final String title;

    public String displayName() {
        return this.title;
    }

}
