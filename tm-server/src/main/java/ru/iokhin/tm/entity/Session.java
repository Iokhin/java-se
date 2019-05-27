package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Getter
@Setter
public class Session extends AbstractEntity {

    @Nullable
    private String signature;

    @Nullable
    private Date timeStamp;

    @Override
    public String toString() {
        return "Session{" +
                "userId = " + parentId + '\'' +
                ", signature ='" + signature + '\'' +
                ", timeStamp =" + timeStamp +
                ", id='" + id + '\'' +
                '}';
    }
}
