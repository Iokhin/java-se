package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.iokhin.tm.api.IEntity;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEntity implements IEntity {

    protected String parentId = "";
    protected String id = UUID.randomUUID().toString();
    protected String name = "";
    protected String description = "";

}
