package ru.iokhin.tm;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.endpoint.SessionEndpointBean;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.service.*;
import ru.iokhin.tm.util.DBConnect;
import ru.iokhin.tm.util.PropertiesUtil;

import javax.xml.ws.Endpoint;
import java.sql.*;
import java.util.Properties;

@Getter
@Setter
final class Bootstrap {

    @NotNull
    private final IServiceLocator serviceLocator = new ServiceLocator();

    @SneakyThrows
    void init() {
        Properties properties = PropertiesUtil.getProperties(Application.class);
        Connection connection = DBConnect.connect(properties);

        Endpoint.publish("http://localhost:8080/ProjectEndpointBean", new ProjectEndpointBean(serviceLocator));
        Endpoint.publish("http://localhost:8080/SessionEndpointBean", new SessionEndpointBean(serviceLocator));
        Endpoint.publish("http://localhost:8080/TaskEndpointBean", new TaskEndpointBean(serviceLocator));
        Endpoint.publish("http://localhost:8080/UserEndpointBean", new UserEndpointBean(serviceLocator));
        generateTestData();
        System.out.println("***WELCOME TO TM-SERVER***");
    }

    private void generateTestData() {
        //Test data
        //---------
        @NotNull final IProjectService projectService = serviceLocator.getProjectService();
        @NotNull final ITaskService taskService = serviceLocator.getTaskService();
        @NotNull final IUserService userService = serviceLocator.getUserService();

        userService.add(RoleType.ADMIN, "3afe899e-ee58-4543-8076-48af7f1abd71", "admin", "admin");
        userService.add(RoleType.USER, "7cfe899e-ee58-3290-8076-48af7f1abd66", "user", "user");

        projectService.add(userService.findByLogin("user").getId(), "Project 1");
        projectService.add(userService.findByLogin("admin").getId(), "Project 2");

        for (Project project : projectService.findAllByUserId(userService.findByLogin("admin").getId())) {
            taskService.add(userService.findByLogin("admin").getId(), project.getId(), "ADMIN TASK 5");
            taskService.add(userService.findByLogin("admin").getId(), project.getId(), "ADMIN TASK 6");
            taskService.add(userService.findByLogin("admin").getId(), project.getId(), "ADMIN TASK 7");
            taskService.add(userService.findByLogin("admin").getId(), project.getId(), "ADMIN TASK 8");
        }

        for (Project project : projectService.findAllByUserId(userService.findByLogin("user").getId())) {
            taskService.add(userService.findByLogin("user").getId(), project.getId(), "USER TASK 1");
            taskService.add(userService.findByLogin("user").getId(), project.getId(), "USER TASK 2");
            taskService.add(userService.findByLogin("user").getId(), project.getId(), "USER TASK 3");
            taskService.add(userService.findByLogin("user").getId(), project.getId(), "USER TASK 4");
        }
        //---------
    }
}
