package org.example.service;

import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;

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
     * Displays the {@link Player}'s usage history
     */
    void getPlayerHistory();

    /**
     * @return Returns the {@link Player}
     */
    Player getPlayer();

    /**
     * add information about player actions
     * @param id Player id
     * @param playerHistory What did the player do
     */
    void addEnumToDatabase(int id, PlayerHistory playerHistory);
}
