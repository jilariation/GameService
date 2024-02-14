package org.example;

import org.example.console.ConsoleInteractionImpl;
import org.example.repository.dto.LiquibaseMigration;

public class Main {
    public static void main(String[] args) {
        LiquibaseMigration.getMigrations();
        new ConsoleInteractionImpl().startWindows();
    }
}
