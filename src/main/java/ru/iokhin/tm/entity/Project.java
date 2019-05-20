package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public final class Project extends AbstractEntity {

    @NotNull
    private String userId;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;


    public Project(@NotNull String name, @NotNull String userId) {
        this.name = name;
        this.userId = userId;
        this.startDate = new Date();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.name, this.id);
    }
}
