package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session")
public final class Session extends AbstractEntity implements Cloneable {

    @Nullable
    protected String parentId;

    @Override
    public Session clone() {
        try {
            return (Session) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    private String signature;

    @Nullable
    private Date timeStamp;

    @Override
    public String toString() {
        return "Session{" +
                "userId = '" + parentId + '\'' +
                ", signature ='" + signature + '\'' +
                ", timeStamp =" + timeStamp +
                ", id='" + id + '\'' +
                '}';
    }
}
