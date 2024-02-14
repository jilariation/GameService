package com.github.gameserivcespring.service.player;

import com.github.gameserivcespring.repository.entity.Player;
import com.github.gameserivcespring.repository.entity.PlayerHistory;

import java.util.List;

public interface PlayerHistoryService {
    void save(PlayerHistory playerHistory);
    List<PlayerHistory> findAllByPlayer(Player player);
}
