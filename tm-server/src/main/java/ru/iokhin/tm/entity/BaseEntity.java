package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.Setter;
import ru.iokhin.tm.enumerated.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity extends AbstractEntity {

    protected String name;

    protected String description;

    @Enumerated(EnumType.STRING)
    protected Status status;

    protected Date dateStart;

    protected Date dateEnd;

}
