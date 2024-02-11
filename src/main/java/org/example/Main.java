package org.example;

import org.example.connection.LiquibaseMigration;

public class Main {
    public static void main(String[] args) {
        LiquibaseMigration.getMigrations();
    }
}
