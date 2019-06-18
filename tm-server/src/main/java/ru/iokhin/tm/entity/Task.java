package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.TaskDTO;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "task")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends BaseEntity implements Serializable {

    @Nullable
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public TaskDTO getTaskDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setName(name);
        taskDTO.setDescription(description);
        taskDTO.setStatus(status);
        taskDTO.setStartDate(dateStart);
        taskDTO.setEndDate(dateEnd);
        taskDTO.setParentId(user.getId());
        taskDTO.setProjectId(project.getId());
        return taskDTO;
    }
}
