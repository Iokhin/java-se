package ru.iokhin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.api.ITerminalService;

import java.util.Scanner;

@Component
@Scope("singleton")
@NoArgsConstructor
public class TerminalService implements ITerminalService {
    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

}
