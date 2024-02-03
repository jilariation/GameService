package org.example.service;

import org.example.repository.entity.Player;

public interface PlayerService {
    int registrationPlayer(String name);
    void loginPlayer(int id);
    void getInformationAboutPlayer();
    void getPlayerHistory();
    Player getPlayer();
}
