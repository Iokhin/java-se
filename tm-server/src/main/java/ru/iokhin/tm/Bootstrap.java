package ru.iokhin.tm;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.repository.ISessionRepository;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.*;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.endpoint.SessionEndpointBean;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.service.*;
import ru.iokhin.tm.util.DBConnect;
import ru.iokhin.tm.util.PropertiesUtil;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;
import java.sql.*;

@Getter
@Setter
final class Bootstrap {

    @NotNull
    private static final PropertiesUtil propertiesUtil = new PropertiesUtil();

    @NotNull
    private static Connection connection = DBConnect.connect(propertiesUtil.getProperties());

    @SneakyThrows
    void init() {
        @NotNull final SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        @NotNull final ISessionService sessionService = new SessionService(sqlSessionFactory);
        @NotNull final IUserService userService = new UserService(sqlSessionFactory);
        @NotNull final IProjectService projectService = new ProjectService(sqlSessionFactory);
        @NotNull final ITaskService taskService = new TaskService(sqlSessionFactory);
        @NotNull final IServiceLocator serviceLocator = new ServiceLocator(sessionService, userService, projectService, taskService);
        Endpoint.publish("http://localhost:8080/ProjectEndpointBean", new ProjectEndpointBean(serviceLocator));
        Endpoint.publish("http://localhost:8080/SessionEndpointBean", new SessionEndpointBean(serviceLocator));
        Endpoint.publish("http://localhost:8080/TaskEndpointBean", new TaskEndpointBean(serviceLocator));
        Endpoint.publish("http://localhost:8080/UserEndpointBean", new UserEndpointBean(serviceLocator));
        generateTestData(serviceLocator);
        System.out.println("***WELCOME TO TM-SERVER***");
    }

    private void generateTestData(IServiceLocator serviceLocator) throws SQLException {
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

    public SqlSessionFactory getSqlSessionFactory() {
        @Nullable final String user = propertiesUtil.getJdbcUsername();
        @Nullable final String password = propertiesUtil.getJdbcPassword();
        @Nullable final String url = propertiesUtil.getJdbcUrl();
        @Nullable final String driver = propertiesUtil.getJdbcDriver();
        final DataSource dataSource = new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        final Environment environment = new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(IUserRepository.class);
        configuration.addMapper(IProjectRepository.class);
        configuration.addMapper(ISessionRepository.class);
        configuration.addMapper(ITaskRepository.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
