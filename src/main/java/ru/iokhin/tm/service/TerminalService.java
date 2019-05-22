package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.ITerminalService;

import java.util.Scanner;

public final class TerminalService implements ITerminalService {

    @NotNull
    final private Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
