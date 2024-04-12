package com.github.gameserivcespring.notificationservice.service;

import com.github.gameserivcespring.notificationservice.repository.dto.PlayerHistoryDTO;
import com.github.gameserivcespring.notificationservice.repository.dto.PlayerHistoryResponse;
import com.github.gameserivcespring.userservice.repository.entity.User;
import com.github.gameserivcespring.notificationservice.repository.entity.PlayerHistory;

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
     * Находит все операции для {@link User}
     * @param user Игрок, который совершал операции
     * @return Возвращает историю игрока
     */
    List<PlayerHistory> findAllByPlayer(User user);

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
