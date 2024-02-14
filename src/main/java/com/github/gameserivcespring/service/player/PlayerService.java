package com.github.gameserivcespring.service.player;


import com.github.gameserivcespring.repository.entity.Player;

import java.util.Optional;

public interface PlayerService {
    /**
     * Registers a {@link Player}
     */
    void save(Player player);
    void update(Player updatedPlayer, int id);
    /**
     * Logins a {@link Player}
     * @param name Player name
     * @param password player password
     */
    Optional<Player> loginPlayer(String name, String password);
    Optional<Player> findPlayerByNameAndPassword(String name, String password);
    Optional<Player> findByName(String name);
    Optional<Player> findById(int id);
}
