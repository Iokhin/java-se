package ru.iokhin.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entityDTO.UserDTO;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.util.DataScope;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.util.StringValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
@Setter
@ApplicationScoped
public class UserService extends AbstractService<UserDTO> implements IUserService {

    @Inject
    public UserService(@NotNull final EntityManagerFactory factory, @NotNull final IProjectService projectService,
                       @NotNull final ITaskService taskService) {
        super(factory);
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Nullable
    private UserDTO currentUser;

    @NotNull
    private IProjectService projectService;

    @NotNull
    private ITaskService taskService;

    @Override
    public UserDTO add(@NotNull final RoleType roleType, @NotNull final String login, @NotNull final String password) {
        StringValidator.validate(login, password);
        UserDTO user = new UserDTO(roleType, login, password);
        merge(user);
        return user;
    }

    //Method for testing data
    @Override
    public UserDTO add(@NotNull RoleType roleType, @NotNull String id, @NotNull String login, @NotNull String password) {
        StringValidator.validate(login, password);
        UserDTO user = new UserDTO(roleType, id, login, password);
        merge(user);
        return user;
    }

    //-----------------------
    @Override
    @SneakyThrows
    public UserDTO edit(@NotNull final String id, @NotNull final String newLogin, @NotNull final String newPassword) {
        @Nullable final UserDTO userDTO = findOne(id);
        if (userDTO == null) return null;
        userDTO.setLogin(newLogin);
        userDTO.setPasswordHash(MD5Util.passwordToHash(newPassword));
        merge(userDTO);
        return userDTO;
    }

    @Override
    public void persist(@NotNull UserDTO userDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @NotNull final User user = getUserFromDTO(userDTO);
        try {
            em.getTransaction().begin();
            userRepository.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull UserDTO userDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @NotNull final User user = getUserFromDTO(userDTO);
        try {
            em.getTransaction().begin();
            userRepository.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public UserDTO findOne(@NotNull String id) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        User user = userRepository.findOne(id);
        if (user == null) return null;
        return user.getUserDTO();
    }

    @Override
    public void remove(@NotNull UserDTO userDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @NotNull final User user = getUserFromDTO(userDTO);
        try {
            em.getTransaction().begin();
            userRepository.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public UserDTO findByLogin(@NotNull final String login) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @NotNull final User user = userRepository.findByLogin(login);
        if (user == null) return null;
        return user.getUserDTO();
    }

    @Override
    public UserDTO authUser(@NotNull String login, @NotNull String password) throws AuthException {
        StringValidator.validate(login, password);
        @NotNull final UserDTO user = findByLogin(login);
        if (user == null) throw new AuthException("WRONG LOGIN");
        if (!user.getPasswordHash().equals(MD5Util.passwordToHash(password)))
            throw new AuthException("WRONG PASSWORD");
        setCurrentUser(user);
        return user;
    }

    @Override
    public boolean changePassword(@NotNull String oldPassword, @NotNull String newPassword) {
        StringValidator.validate(oldPassword, newPassword);
        if (!MD5Util.passwordToHash(oldPassword).equals(getCurrentUser().getPasswordHash()))
            return false;
        UserDTO user = getCurrentUser();
        user.setPasswordHash(MD5Util.passwordToHash(newPassword));
        merge(user);
        return true;
    }

    @Override
    @SneakyThrows
    public void dataBinSave() {
        @NotNull final DataScope dataScope = getDataScope();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serializedData"))) {
            objectOutputStream.writeObject(dataScope);
            objectOutputStream.flush();
            System.out.println("SUCCESS");
        }
    }

    @Override
    @SneakyThrows
    public void dataBinLoad() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serializedData"))) {
            DataScope dataScope = (DataScope) objectInputStream.readObject();
            mergeLoadedData(dataScope);
            System.out.println("SUCCESS");
        }
    }

    @Override
    @SneakyThrows
    public void dataJAXBXMLSave() {
        @NotNull final DataScope dataScope = getDataScope();
        final JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(DataScope.class);
        final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(dataScope, new File("jaxb.xml"));
        System.out.println("SUCCESS");
    }

    @Override
    @SneakyThrows
    public void dataJAXBXMLLoad() {
        DataScope dataScope;
        final JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(DataScope.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        dataScope = (DataScope) unmarshaller.unmarshal(new File("jaxb.xml"));
        mergeLoadedData(dataScope);
        System.out.println("SUCCESS");
    }

    @Override
    @SneakyThrows
    public void dataJAXBJSONSave() {
        @NotNull final DataScope dataScope = getDataScope();
        final JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(DataScope.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(dataScope, new File("jaxb.json"));
        System.out.println("SUCCESS");
    }

    @Override
    @SneakyThrows
    public void dataJAXBJSONLoad() {
        DataScope dataScope;
        final JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(DataScope.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        dataScope = (DataScope) unmarshaller.unmarshal(new File("jaxb.json"));
        mergeLoadedData(dataScope);
        System.out.println("SUCCESS");
    }

    @Override
    @SneakyThrows
    public void dataFasterXMLSave() {
        @NotNull final DataScope dataScope = getDataScope();
        @NotNull final XmlMapper mapper = new XmlMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("faster.xml"), dataScope);
        System.out.println("SUCCESS");
    }

    @Override
    @SneakyThrows
    public void dataFasterXMLLoad() {
        @NotNull final DataScope dataScope;
        @NotNull final XmlMapper mapper = new XmlMapper();
        dataScope = mapper.readValue(new File("faster.xml"), DataScope.class);
        mergeLoadedData(dataScope);
        System.out.println("SUCCESS");
    }

    @Override
    @SneakyThrows
    public void dataFasterJSONLoad() {
        DataScope dataScope;
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        dataScope = mapper.readValue(new File("faster.json"), DataScope.class);
        mergeLoadedData(dataScope);
        System.out.println("SUCCESS");
    }

    @Override
    public void dataFasterJSONSave() throws IOException {
        @NotNull final DataScope dataScope = getDataScope();
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("faster.json"), dataScope);
        System.out.println("SUCCESS");
    }

    @Override
    public User getUserFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPasswordHash(userDTO.getPasswordHash());
        user.setRole(userDTO.getRoleType());
        return user;
    }

    private DataScope getDataScope() {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        return new DataScope(new ArrayList<>(projectRepository.findAll().stream().map(Project::getProjectDTO).collect(Collectors.toList())),
                new ArrayList<>(taskRepository.findAll().stream().map(Task::getTaskDTO).collect(Collectors.toList())),
                new ArrayList<>(userRepository.findAll().stream().map(User::getUserDTO).collect(Collectors.toList())));
    }

    private void mergeLoadedData(DataScope dataScope) {
        dataScope.getUsers().forEach(this::merge);
        dataScope.getProjects().forEach(projectService::merge);
        dataScope.getTasks().forEach(taskService::merge);
    }
}
