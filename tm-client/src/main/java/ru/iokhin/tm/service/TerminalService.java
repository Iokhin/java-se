package ru.iokhin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.ITerminalService;

import javax.enterprise.context.ApplicationScoped;
import java.util.Scanner;

@ApplicationScoped
@NoArgsConstructor
public class TerminalService implements ITerminalService {
    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

}
