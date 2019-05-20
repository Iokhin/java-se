package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEntity {

    protected String id = UUID.randomUUID().toString();
    protected String name = "";
    protected String description = "";

}
