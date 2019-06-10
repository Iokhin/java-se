package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.DTO.SessionDTO;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session")
public class Session extends AbstractEntity implements Serializable {

    @Nullable
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Nullable
    private String signature;

    @Nullable
    private Date timeStamp;

    public SessionDTO getSessionDTO() {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(id);
        sessionDTO.setParentId(user.getId());
        sessionDTO.setSignature(signature);
        sessionDTO.setTimeStamp(timeStamp);
        return sessionDTO;
    }

}
