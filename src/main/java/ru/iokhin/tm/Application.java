package ru.iokhin.tm;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.project.*;
import ru.iokhin.tm.command.system.AboutCommand;
import ru.iokhin.tm.command.system.ExitCommand;
import ru.iokhin.tm.command.system.HelpCommand;
import ru.iokhin.tm.command.task.*;
import ru.iokhin.tm.command.user.*;

public final class Application {

    private static final Class[] CLASSES = {
            HelpCommand.class, AboutCommand.class, ExitCommand.class,
            ProjectCreateCommand.class, ProjectEditCommand.class,
            ProjectListCommand.class, ProjectRemoveAllCommand.class,
            ProjectRemoveCommand.class, TaskCreateCommand.class,
            TaskEditCommand.class, TaskListCommand.class,
            TaskRemoveAllByProjectCommand.class, TaskRemoveCommand.class,
            UserAuthorizationCommand.class, UserEndSessionCommand.class,
            UserListCommand.class, UserPasswordChangeCommand.class,
            UserProfileEditCommand.class, UserRegistrationCommand.class,
            TaskRemoveAllByUserCommand.class, TaskListAllCommand.class,
            ProjectSortListCommand.class, TaskSortListCommand.class,
            ProjectFindByPartCommand.class
    };

    public static void main(@NotNull String[] args) {

        @NotNull final Bootstrap bootstrap = new Bootstrap();

        bootstrap.init(CLASSES);
    }
}
