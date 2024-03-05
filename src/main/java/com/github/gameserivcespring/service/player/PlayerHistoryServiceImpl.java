package com.github.gameserivcespring.service.player;

import com.github.gameserivcespring.repository.history.PlayerHistoryRepository;
import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.history.entity.PlayerHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerHistoryServiceImpl implements PlayerHistoryService {
    private final PlayerHistoryRepository playerHistoryRepository;

    @Autowired
    public PlayerHistoryServiceImpl(PlayerHistoryRepository playerHistoryRepository) {
        this.playerHistoryRepository = playerHistoryRepository;
    }

    @Override
    public void save(PlayerHistory playerHistory) {
        playerHistoryRepository.save(playerHistory);
    }

    @Override
    public List<PlayerHistory> findAllByPlayer(Player player) {
        return playerHistoryRepository.findAllByPlayer(player);
    }
}
