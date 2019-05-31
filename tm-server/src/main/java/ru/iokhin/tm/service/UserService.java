package ru.iokhin.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.DataScope;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.util.StringValidator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.SOAPException;
import java.io.*;
import java.util.ArrayList;

@Getter
@Setter
public class UserService extends AbstractService<User, IUserRepository> implements IUserService {

    @Nullable
    private User currentUser;

    @NotNull
    private final IProjectRepository projectRepository;

    @NotNull
    private final ITaskRepository taskRepository;

    UserService(@NotNull final IUserRepository repository,
                @NotNull final IProjectRepository projectRepository,
                @NotNull final ITaskRepository taskRepository) {
        super(repository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public User add(@NotNull final RoleType roleType, @NotNull final String login, @NotNull final String password) {
        return repository.persist(new User(roleType, login, password));
    }

    //Method for testing data load
    @Override
    public User add(@NotNull RoleType roleType, @NotNull String id, @NotNull String login, @NotNull String password) {
        return repository.persist(new User(roleType, id, login, password));
    }

    @Override
    public User edit(@NotNull final String userId, @NotNull final String newLogin, @NotNull final String newPassword) {
        @Nullable final User user = findOne(userId);
        if (user == null) return null;
        user.setLogin(newLogin);
        user.setPasswordHash(MD5Util.passwordToHash(newPassword));
        return user;
    }

    @Override
    public User findByLogin(@NotNull final String login) {
        return repository.findByLogin(login);
    }

    @Override
    public User authUser(@NotNull String login, @NotNull String password) throws AuthException {
        StringValidator.validate(login, password);
        @NotNull final User user = repository.findByLogin(login);
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
        getCurrentUser().setPasswordHash(MD5Util.passwordToHash(newPassword));
        return true;
    }

    @Override
    public void dataBinSave() throws IOException {
        @NotNull final DataScope dataScope = new DataScope(new ArrayList<>(projectRepository.findAll()),
                new ArrayList<>(taskRepository.findAll()),
                new ArrayList<>(repository.findAll()));
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serializedData"))) {
            objectOutputStream.writeObject(dataScope);
            objectOutputStream.flush();
            System.out.println("SUCCESS");
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void dataBinLoad() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serializedData"))) {
            DataScope dataScope = (DataScope) objectInputStream.readObject();
            dataScope.getProjects().forEach(projectRepository::merge);
            dataScope.getTasks().forEach(taskRepository::merge);
            dataScope.getUsers().forEach(repository::merge);
            System.out.println("SUCCESS");
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void dataJAXBXMLSave() throws JAXBException {
        @NotNull final DataScope dataScope = new DataScope(new ArrayList<>(projectRepository.findAll()),
                new ArrayList<>(taskRepository.findAll()),
                new ArrayList<>(repository.findAll()));
        final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(DataScope.class);
            final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(dataScope, new File("jaxb.xml"));
            System.out.println("SUCCESS");
        } catch (JAXBException e) {
            throw e;
        }
    }

    @Override
    public void dataJAXBXMLLoad() throws JAXBException {
        DataScope dataScope;
        final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(DataScope.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            dataScope = (DataScope) unmarshaller.unmarshal(new File("jaxb.xml"));
            dataScope.getProjects().forEach(projectRepository::merge);
            dataScope.getTasks().forEach(taskRepository::merge);
            dataScope.getUsers().forEach(repository::merge);
            System.out.println("SUCCESS");
        } catch (JAXBException e) {
            throw e;
        }
    }

    @Override
    public void dataJAXBJSONSave() throws JAXBException {
        @NotNull final DataScope dataScope = new DataScope(new ArrayList<>(projectRepository.findAll()),
                new ArrayList<>(taskRepository.findAll()),
                new ArrayList<>(repository.findAll()));
        final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(DataScope.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(dataScope, new File("jaxb.json"));
            System.out.println("SUCCESS");
        } catch (JAXBException e) {
            throw e;
        }
    }

    @Override
    public void dataJAXBJSONLoad() throws JAXBException {
        DataScope dataScope;
        final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(DataScope.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            dataScope = (DataScope) unmarshaller.unmarshal(new File("jaxb.json"));
            dataScope.getUsers().forEach(repository::merge);
            dataScope.getProjects().forEach(projectRepository::merge);
            dataScope.getTasks().forEach(taskRepository::merge);
            System.out.println("SUCCESS");
        } catch (JAXBException e) {
            throw e;
        }
    }

    @Override
    public void dataFasterXMLSave() throws IOException {
        @NotNull final DataScope dataScope = new DataScope(new ArrayList<>(projectRepository.findAll()),
                new ArrayList<>(taskRepository.findAll()),
                new ArrayList<>(repository.findAll()));
        @NotNull final XmlMapper mapper = new XmlMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("faster.xml"), dataScope);
            System.out.println("SUCCESS");
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void dataFasterXMLLoad() throws IOException {
        @NotNull final DataScope dataScope = new DataScope(new ArrayList<>(projectRepository.findAll()),
                new ArrayList<>(taskRepository.findAll()),
                new ArrayList<>(repository.findAll()));
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("faster.json"), dataScope);
            System.out.println("SUCCESS");
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void dataFasterJSONLoad() throws IOException {
        DataScope dataScope;
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        try {
            dataScope = mapper.readValue(new File("faster.json"), DataScope.class);
            dataScope.getUsers().forEach(repository::merge);
            dataScope.getProjects().forEach(projectRepository::merge);
            dataScope.getTasks().forEach(taskRepository::merge);
            System.out.println("SUCCESS");
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void dataFasterJSONSave() throws IOException {
        DataScope dataScope;
        @NotNull final XmlMapper mapper = new XmlMapper();
        try {
            dataScope = mapper.readValue(new File("faster.xml"), DataScope.class);
            dataScope.getUsers().forEach(repository::merge);
            dataScope.getProjects().forEach(projectRepository::merge);
            dataScope.getTasks().forEach(taskRepository::merge);
            System.out.println("SUCCESS");
        } catch (IOException e) {
            throw e;
        }
    }
}
