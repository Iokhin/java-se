package ru.iokhin.tm;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.data.*;
import ru.iokhin.tm.command.project.*;
import ru.iokhin.tm.command.system.AboutCommand;
import ru.iokhin.tm.command.system.ExitCommand;
import ru.iokhin.tm.command.system.HelpCommand;
import ru.iokhin.tm.command.task.*;
import ru.iokhin.tm.command.user.*;

public final class Application {
    public static void main(@NotNull String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
