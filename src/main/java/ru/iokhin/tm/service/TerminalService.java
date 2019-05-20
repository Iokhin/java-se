package ru.iokhin.tm.service;

import ru.iokhin.tm.api.service.ITerminalService;

import java.util.Scanner;

public class TerminalService implements ITerminalService {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
