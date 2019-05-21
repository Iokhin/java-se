package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.ReadinessStatus;


import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public final class Project extends AbstractEntity {

    @NotNull
    private String parentId;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    @NotNull
    private final ReadinessStatus status = ReadinessStatus.PLANNIG;

    public Project(@NotNull String userId, @NotNull String name) {
        this.name = name;
        this.parentId = userId;
        this.startDate = new Date();
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.name, this.id);
    }
}
