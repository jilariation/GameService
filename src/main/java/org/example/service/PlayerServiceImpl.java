package org.example.service;

import org.example.repository.PlayerRepository;
import org.example.repository.dto.DatabaseConnection;
import org.example.repository.entity.Player;

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
    public void getInformationAboutPlayer() {
        playerRepository.getInformationAboutPlayer();
    }
    @Override
    public void getPlayerHistory() {
        playerRepository.getPlayerHistory();
    }
    @Override
    public Player getPlayer() {
        return playerRepository.getPlayer();
    }
}
