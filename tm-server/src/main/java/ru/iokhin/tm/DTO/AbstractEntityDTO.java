package ru.iokhin.tm.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEntityDTO implements Serializable {
    @NotNull
    @Id
    protected String id = UUID.randomUUID().toString();
    @Nullable
    protected String name = "";
    @Nullable
    protected String description = "";
}
