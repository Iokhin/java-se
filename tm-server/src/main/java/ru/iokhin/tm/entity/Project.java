package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.DTO.ProjectDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project extends BaseEntity implements Serializable {

    @Nullable
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Nullable
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Task> tasks;

    public ProjectDTO getProjectDTO() {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(id);
        projectDTO.setName(name);
        projectDTO.setDescription(description);
        projectDTO.setParentId(user.getId());
        projectDTO.setStatus(status);
        projectDTO.setStartDate(dateStart);
        projectDTO.setEndDate(dateEnd);
        return projectDTO;
    }

}
