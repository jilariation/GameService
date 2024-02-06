package org.example.console;

public interface ConsoleInteraction {
    /**
     * A welcome start window
     */
    void startWindows();

    /**
     * The window for user registration
     */
    void registrationWindow();

    /**
     * The window for user authorization
     */
    void loginWindow();

    /**
     * Window for making a debit transaction
     */
    void debitWindow();

    /**
     * Window for making a credit transaction
     */
    void creditWindow();

    /**
     * A menu window for basic player interaction
     */
    void menuWindow();
}
