package ru.iokhin.tm.enumerated;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public enum Status {
    PLANNING("Запланировано"),
    PROCCESSING("В процессе"),
    READY("Готово");

    @NotNull
    private final String title;

    @Override
    public String toString() {
        return this.title;
    }
}
