package com.github.gameserivcespring.service.player;

import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.history.entity.PlayerHistory;

import java.util.List;

/**
 * Сервис для {@link PlayerHistory}
 */
public interface PlayerHistoryService {
    /**
     * Сохраняет операцию игрока в БД
     * @param playerHistory Операция, которую совершил игрок
     */
    void save(PlayerHistory playerHistory);
    /**
     * Находит все операции для {@link Player}
     * @param player Игрок, который совершал операции
     * @return Возвращает историю игрока
     */
    List<PlayerHistory> findAllByPlayer(Player player);
}
