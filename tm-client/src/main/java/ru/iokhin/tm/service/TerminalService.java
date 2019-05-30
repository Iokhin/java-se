package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.ITerminalService;

import java.util.Scanner;

public class TerminalService implements ITerminalService {
    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
