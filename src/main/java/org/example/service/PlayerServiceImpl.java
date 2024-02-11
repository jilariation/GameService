package org.example.service;

import org.example.repository.PlayerRepository;
import org.example.connection.DatabaseConnection;
import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;

/**
 * Implementation of {@link PlayerService} interface
 */
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository = new PlayerRepository(new DatabaseConnection().getConnection());

    public void registrationPlayer(String name, String password) {
        playerRepository.registrationPlayer(name, password);
    }
    public void loginPlayer(String name, String password) {
        playerRepository.loginPlayer(name, password);
    }
    @Override
    public void getPlayerHistory() {
        playerRepository.getPlayerHistory();
    }
    @Override
    public Player getPlayer() {
        return playerRepository.getPlayer();
    }
    @Override
    public void addEnumToDatabase(int id, PlayerHistory playerHistory) {
        playerRepository.addEnumToDatabase(id, playerHistory);
    }
}
