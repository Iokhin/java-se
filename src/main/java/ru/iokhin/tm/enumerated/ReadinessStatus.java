package ru.iokhin.tm.enumerated;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public enum ReadinessStatus {
    PLANNING("Запланировано"),
    PROCCESSING("В процессе"),
    READY("Готово");

    @NotNull
    private final String title;

    public String displayName() {
        return this.title;
    }

}
