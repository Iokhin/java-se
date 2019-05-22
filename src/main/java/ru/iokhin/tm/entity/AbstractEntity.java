package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IEntity;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEntity implements IEntity {

    @Nullable protected String parentId = "";
    @NotNull protected String id = UUID.randomUUID().toString();
    @Nullable protected String name = "";
    @Nullable protected String description = "";

}
