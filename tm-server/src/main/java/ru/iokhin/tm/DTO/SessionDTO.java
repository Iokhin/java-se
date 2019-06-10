package ru.iokhin.tm.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class SessionDTO extends AbstractEntityDTO implements Cloneable {

    @Nullable
    protected String parentId;

    @Override
    public SessionDTO clone() {
        try {
            return (SessionDTO) super.clone();
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
        return "SessionDTO{" +
                "userId = '" + parentId + '\'' +
                ", signature ='" + signature + '\'' +
                ", timeStamp =" + timeStamp +
                ", id='" + id + '\'' +
                '}';
    }
}
