//package ru.iokhin.tm.config;
//
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import ru.iokhin.tm.command.AbstractCommand;
//import ru.iokhin.tm.command.data.*;
//import ru.iokhin.tm.command.project.*;
//import ru.iokhin.tm.command.system.AboutCommand;
//import ru.iokhin.tm.command.system.ExitCommand;
//import ru.iokhin.tm.command.system.HelpCommand;
//import ru.iokhin.tm.command.system.ServerInfoCommand;
//import ru.iokhin.tm.command.task.*;
//import ru.iokhin.tm.command.user.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class CommandRegister {
//
//    @Autowired
//    @NotNull
//    private HelpCommand helpCommand;
//    @Autowired
//    @NotNull
//    private AboutCommand aboutCommand;
//    @Autowired
//    @NotNull
//    private ExitCommand exitCommand;
//    @Autowired
//    @NotNull
//    private ProjectCreateCommand projectCreateCommand;
//    @Autowired
//    @NotNull
//    private ProjectEditCommand projectEditCommand;
//    @Autowired
//    @NotNull
//    private ProjectListCommand projectListCommand;
//    @Autowired
//    @NotNull
//    private ProjectRemoveAllCommand projectRemoveAllCommand;
//    @Autowired
//    @NotNull
//    private ProjectRemoveCommand projectRemoveCommand;
//    @Autowired
//    @NotNull
//    private TaskCreateCommand taskCreateCommand;
//    @Autowired
//    @NotNull
//    private TaskEditCommand taskEditCommand;
//    @Autowired
//    @NotNull
//    private TaskListCommand taskListCommand;
//    @Autowired
//    @NotNull
//    private TaskRemoveAllByProjectCommand taskRemoveAllByProjectCommand;
//    @Autowired
//    @NotNull
//    private TaskRemoveCommand taskRemoveCommand;
//    @Autowired
//    @NotNull
//    private UserAuthorizationCommand userAuthorizationCommand;
//    @Autowired
//    @NotNull
//    private UserEndSessionCommand userEndSessionCommand;
//    @Autowired
//    @NotNull
//    private UserPasswordChangeCommand userPasswordChangeCommand;
//    @Autowired
//    @NotNull
//    private UserProfileEditCommand userProfileEditCommand;
//    @Autowired
//    @NotNull
//    private UserRegistrationCommand userRegistrationCommand;
//    @Autowired
//    @NotNull
//    private TaskRemoveAllByUserCommand taskRemoveAllByUserCommand;
//    @Autowired
//    @NotNull
//    private TaskListAllCommand taskListAllCommand;
//    @Autowired
//    @NotNull
//    private ProjectSortListCommand projectSortListCommand;
//    @Autowired
//    @NotNull
//    private TaskSortListCommand taskSortListCommand;
//    @Autowired
//    @NotNull
//    private ProjectFindByPartCommand projectFindByPartCommand;
//    @Autowired
//    @NotNull
//    private TaskFindByPartCommand taskFindByPartCommand;
//    @Autowired
//    @NotNull
//    private DataSaveSerializedCommand dataSaveSerializedCommand;
//    @Autowired
//    @NotNull
//    private DataLoadSerializedCommand dataLoadSerializedCommand;
//    @Autowired
//    @NotNull
//    private DataSaveJAXBToXMLCommand dataSaveJAXBToXMLCommand;
//    @Autowired
//    @NotNull
//    private DataLoadJAXBXMLCommand dataLoadJAXBXMLCommand;
//    @Autowired
//    @NotNull
//    private DataSaveJAXBJSONCommand dataSaveJAXBJSONCommand;
//    @Autowired
//    @NotNull
//    private DataLoadJAXBFromJSONCommand dataLoadJAXBFromJSONCommand;
//    @Autowired
//    @NotNull
//    private DataSaveFasterXMLCommand dataSaveFasterXMLCommand;
//    @Autowired
//    @NotNull
//    private DataLoadFasterXMLCommand dataLoadFasterXMLCommand;
//    @Autowired
//    @NotNull
//    private DataSaveFasterJSONCommand dataSaveFasterJSONCommand;
//    @Autowired
//    @NotNull
//    private DataLoadFasterJSONCommand dataLoadFasterJSONCommand;
//    @Autowired
//    @NotNull
//    private ServerInfoCommand serverInfoCommand;
//
//    @Bean
//    Map<String, AbstractCommand> register() {
//        @NotNull final Map<String, AbstractCommand> commandMap = new HashMap<>();
//        AbstractCommand[] commands = {
//                helpCommand, aboutCommand, exitCommand, projectCreateCommand, projectEditCommand, projectListCommand,
//                projectRemoveAllCommand, projectRemoveCommand, taskCreateCommand, taskEditCommand, taskListCommand,
//                taskRemoveAllByProjectCommand, taskRemoveCommand, userAuthorizationCommand, userEndSessionCommand,
//                userPasswordChangeCommand, userProfileEditCommand, userRegistrationCommand, taskRemoveAllByUserCommand,
//                taskListAllCommand, projectSortListCommand, taskSortListCommand, projectFindByPartCommand, taskFindByPartCommand,
//                dataSaveSerializedCommand, dataLoadSerializedCommand, dataSaveJAXBToXMLCommand, dataLoadJAXBXMLCommand,
//                dataSaveJAXBJSONCommand, dataLoadJAXBFromJSONCommand, dataSaveFasterXMLCommand, dataLoadFasterXMLCommand,
//                dataSaveFasterJSONCommand, dataLoadFasterJSONCommand, serverInfoCommand
//        };
//        for (AbstractCommand command : commands) {
//            commandMap.put(command.name(), command);
//        }
//        return commandMap;
//    }
//}
