package ru.iokhin.tm.command.system;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;

@Component
@NoArgsConstructor
public final class ExitCommand extends AbstractCommand {

    @Override
    public final boolean security() {
        return false;
    }

    @Override
    public boolean admin() {
        return false;
    }

    @Override
    public final String name() {
        return "exit";
    }

    @Override
    public final String description() {
        return "Exit the application";
    }

    @Override
    public final void execute() {
        System.exit(0);
    }
}
