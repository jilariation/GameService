package org.example.service;

import org.example.repository.PlayerRepository;
import org.example.repository.entity.Player;

public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository = new PlayerRepository();

    public int registrationPlayer(String name) {
        return playerRepository.registrationPlayer(name);
    }
    public void loginPlayer(int id) {
        playerRepository.loginPlayer(id);
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
        return PlayerRepository.getPlayer();
    }
}
