package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
public class Session extends AbstractEntity implements Cloneable {

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
