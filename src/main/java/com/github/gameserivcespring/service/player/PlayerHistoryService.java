package com.github.gameserivcespring.service.player;

import com.github.gameserivcespring.repository.history.dto.PlayerHistoryDTO;
import com.github.gameserivcespring.repository.history.dto.PlayerHistoryResponse;
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

    /**
     * Преобразует {@link PlayerHistory} в {@link PlayerHistoryDTO}
     * @param playerHistory История игрока
     * @return Возращает DTO истории игрока
     */
    PlayerHistoryDTO convertToPlayerHistoryDTO(PlayerHistory playerHistory);

    /**
     * Формирует response со всеми действиями игрока
     * @param id ID игрока
     * @return Возращает {@link List<PlayerHistoryResponse>}
     */
    List<PlayerHistoryResponse> getAllPlayerHistory(int id);
}
