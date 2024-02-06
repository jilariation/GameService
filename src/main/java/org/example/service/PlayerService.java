package org.example.service;

import org.example.repository.entity.Player;

/**
 *Service for player interaction with the application
 */
public interface PlayerService {
    /**
     * Registers a {@link Player}
     * @param name Player name
     * @param password Player password
     */
    void registrationPlayer(String name, String password);
    /**
     * Logins a {@link Player}
     * @param name Player name
     * @param password player password
     */
    void loginPlayer(String name, String password);

    /**
     * Displays all information about the {@link Player}
     */
    void getInformationAboutPlayer();

    /**
     * Displays the {@link Player}'s usage history
     */
    void getPlayerHistory();

    /**
     * @return Returns the {@link Player}
     */
    Player getPlayer();
}
