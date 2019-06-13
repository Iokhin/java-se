package ru.iokhin.tm.config;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.entityDTO.ProjectDTO;
import ru.iokhin.tm.enumerated.RoleType;

import javax.inject.Inject;
import java.sql.SQLException;

public class TestDataGenerator {

    @NotNull
    private IUserService userService;

    @NotNull
    private IProjectService projectService;

    @NotNull
    private ITaskService taskService;

    @Inject
    public TestDataGenerator(@NotNull IUserService userService,
                             @NotNull IProjectService projectService,
                             @NotNull ITaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    void generateTestData() throws SQLException {
        userService.add(RoleType.ADMIN, "3afe899e-ee58-4543-8076-48af7f1abd71", "admin", "admin");
        userService.add(RoleType.USER, "7cfe899e-ee58-3290-8076-48af7f1abd66", "user", "user");

        projectService.add(userService.findByLogin("user").getId(), "Project 1");
        projectService.add(userService.findByLogin("admin").getId(), "Project 2");

        for (ProjectDTO project : projectService.findAllByUserId(userService.findByLogin("admin").getId())) {
            taskService.add(userService.findByLogin("admin").getId(), project.getId(), "ADMIN TASK 5");
            taskService.add(userService.findByLogin("admin").getId(), project.getId(), "ADMIN TASK 6");
            taskService.add(userService.findByLogin("admin").getId(), project.getId(), "ADMIN TASK 7");
            taskService.add(userService.findByLogin("admin").getId(), project.getId(), "ADMIN TASK 8");
        }

        for (ProjectDTO project : projectService.findAllByUserId(userService.findByLogin("user").getId())) {
            taskService.add(userService.findByLogin("user").getId(), project.getId(), "USER TASK 1");
            taskService.add(userService.findByLogin("user").getId(), project.getId(), "USER TASK 2");
            taskService.add(userService.findByLogin("user").getId(), project.getId(), "USER TASK 3");
            taskService.add(userService.findByLogin("user").getId(), project.getId(), "USER TASK 4");
        }
    }
}
