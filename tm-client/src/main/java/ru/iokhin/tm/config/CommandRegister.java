package ru.iokhin.tm.config;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.command.data.*;
import ru.iokhin.tm.command.project.*;
import ru.iokhin.tm.command.system.AboutCommand;
import ru.iokhin.tm.command.system.ExitCommand;
import ru.iokhin.tm.command.system.HelpCommand;
import ru.iokhin.tm.command.system.ServerInfoCommand;
import ru.iokhin.tm.command.task.*;
import ru.iokhin.tm.command.user.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CommandRegister {

    @Inject
    @NotNull
    private HelpCommand helpCommand;
    @Inject
    @NotNull
    private AboutCommand aboutCommand;
    @Inject
    @NotNull
    private ExitCommand exitCommand;
    @Inject
    @NotNull
    private ProjectCreateCommand projectCreateCommand;
    @Inject
    @NotNull
    private ProjectEditCommand projectEditCommand;
    @Inject
    @NotNull
    private ProjectListCommand projectListCommand;
    @Inject
    @NotNull
    private ProjectRemoveAllCommand projectRemoveAllCommand;
    @Inject
    @NotNull
    private ProjectRemoveCommand projectRemoveCommand;
    @Inject
    @NotNull
    private TaskCreateCommand taskCreateCommand;
    @Inject
    @NotNull
    private TaskEditCommand taskEditCommand;
    @Inject
    @NotNull
    private TaskListCommand taskListCommand;
    @Inject
    @NotNull
    private TaskRemoveAllByProjectCommand taskRemoveAllByProjectCommand;
    @Inject
    @NotNull
    private TaskRemoveCommand taskRemoveCommand;
    @Inject
    @NotNull
    private UserAuthorizationCommand userAuthorizationCommand;
    @Inject
    @NotNull
    private UserEndSessionCommand userEndSessionCommand;
    @Inject
    @NotNull
    private UserPasswordChangeCommand userPasswordChangeCommand;
    @Inject
    @NotNull
    private UserProfileEditCommand userProfileEditCommand;
    @Inject
    @NotNull
    private UserRegistrationCommand userRegistrationCommand;
    @Inject
    @NotNull
    private TaskRemoveAllByUserCommand taskRemoveAllByUserCommand;
    @Inject
    @NotNull
    private TaskListAllCommand taskListAllCommand;
    @Inject
    @NotNull
    private ProjectSortListCommand projectSortListCommand;
    @Inject
    @NotNull
    private TaskSortListCommand taskSortListCommand;
    @Inject
    @NotNull
    private ProjectFindByPartCommand projectFindByPartCommand;
    @Inject
    @NotNull
    private TaskFindByPartCommand taskFindByPartCommand;
    @Inject
    @NotNull
    private DataSaveSerializedCommand dataSaveSerializedCommand;
    @Inject
    @NotNull
    private DataLoadSerializedCommand dataLoadSerializedCommand;
    @Inject
    @NotNull
    private DataSaveJAXBToXMLCommand dataSaveJAXBToXMLCommand;
    @Inject
    @NotNull
    private DataLoadJAXBXMLCommand dataLoadJAXBXMLCommand;
    @Inject
    @NotNull
    private DataSaveJAXBJSONCommand dataSaveJAXBJSONCommand;
    @Inject
    @NotNull
    private DataLoadJAXBFromJSONCommand dataLoadJAXBFromJSONCommand;
    @Inject
    @NotNull
    private DataSaveFasterXMLCommand dataSaveFasterXMLCommand;
    @Inject
    @NotNull
    private DataLoadFasterXMLCommand dataLoadFasterXMLCommand;
    @Inject
    @NotNull
    private DataSaveFasterJSONCommand dataSaveFasterJSONCommand;
    @Inject
    @NotNull
    private DataLoadFasterJSONCommand dataLoadFasterJSONCommand;
    @Inject
    @NotNull
    private ServerInfoCommand serverInfoCommand;

    @Produces
    Map<String, AbstractCommand> register() {
        @NotNull final Map<String, AbstractCommand> commandMap = new HashMap<>();
        AbstractCommand[] commands = {
                helpCommand, aboutCommand, exitCommand, projectCreateCommand, projectEditCommand, projectListCommand,
                projectRemoveAllCommand, projectRemoveCommand, taskCreateCommand, taskEditCommand, taskListCommand,
                taskRemoveAllByProjectCommand, taskRemoveCommand, userAuthorizationCommand, userEndSessionCommand,
                userPasswordChangeCommand, userProfileEditCommand, userRegistrationCommand, taskRemoveAllByUserCommand,
                taskListAllCommand, projectSortListCommand, taskSortListCommand, projectFindByPartCommand, taskFindByPartCommand,
                dataSaveSerializedCommand, dataLoadSerializedCommand, dataSaveJAXBToXMLCommand, dataLoadJAXBXMLCommand,
                dataSaveJAXBJSONCommand, dataLoadJAXBFromJSONCommand, dataSaveFasterXMLCommand, dataLoadFasterXMLCommand,
                dataSaveFasterJSONCommand, dataLoadFasterJSONCommand, serverInfoCommand
        };
        for (AbstractCommand command : commands) {
            commandMap.put(command.name(), command);
        }
        return commandMap;
    }
}
