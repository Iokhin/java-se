package ru.iokhin.tm;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IEndpointServiceLocator;
import ru.iokhin.tm.command.data.*;
import ru.iokhin.tm.command.project.*;
import ru.iokhin.tm.command.system.*;
import ru.iokhin.tm.command.task.*;
import ru.iokhin.tm.command.user.*;
import ru.iokhin.tm.endpoint.AuthException_Exception;
import ru.iokhin.tm.service.EndpointServiceLocator;

public class Application {

    private static final Class[] CLASSES = {
            HelpCommand.class, AboutCommand.class, ExitCommand.class,
            ProjectCreateCommand.class, ProjectEditCommand.class,
            ProjectListCommand.class, ProjectRemoveAllCommand.class,
            ProjectRemoveCommand.class, TaskCreateCommand.class,
            TaskEditCommand.class, TaskListCommand.class,
            TaskRemoveAllByProjectCommand.class, TaskRemoveCommand.class,
            UserAuthorizationCommand.class, UserEndSessionCommand.class,
            UserPasswordChangeCommand.class,
            UserProfileEditCommand.class, UserRegistrationCommand.class,
            TaskRemoveAllByUserCommand.class, TaskListAllCommand.class,
            ProjectSortListCommand.class, TaskSortListCommand.class,
            ProjectFindByPartCommand.class, TaskFindByPartCommand.class,
            DataSaveSerializedCommand.class, DataLoadSerializedCommand.class,
            DataSaveJAXBToXMLCommand.class, DataLoadJAXBXMLCommand.class,
            DataSaveJAXBJSONCommand.class, DataLoadJAXBFromJSONCommand.class,
            DataSaveFasterXMLCommand.class, DataLoadFasterXMLCommand.class,
            DataSaveFasterJSONCommand.class, DataLoadFasterJSONCommand.class
    };

    public static void main(@NotNull String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(CLASSES);
    }

}