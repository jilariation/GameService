package com.github.gameserivcespring.service.player;


import com.github.gameserivcespring.repository.entity.Player;

import java.util.Optional;

/**
 * Сервис для {@link Player}
 */
public interface PlayerService {
    /**
     * Регистрирует игрока {@link Player}
     */
    void save(Player player);

    /**
     * Обновляет игрока в БД
     * @param updatedPlayer Обновленные данные об игроке
     * @param id ID игрока
     */
    void update(Player updatedPlayer, int id);
    /**
     * Авторизует {@link Player}
     * @param mail Почта игрока
     * @param password Пароль игрока
     */
    Player loginPlayer(String mail, String password);

    /**
     * Находит игрока с таким логином и паролем
     * @param mail Логиг(почта) игрока
     * @param password Пароль игрока
     * @return Возвращает либо игрока, либо null
     */
    Player findPlayerByMailAndPassword(String mail, String password);

    /**
     * Находит игрока по его почте
     * @param mail Почта игрока
     * @return Возвращает либо игрока, либо null
     */
    Player findByMail(String mail);

    /**
     * Находит игрока по его ID
     * @param id ID игрока
     * @return Возращает либо игрока, либо null
     */
    Player findById(int id);

    /**
     * Удаляет игрока по его ID
     * @param id ID игрока
     */
    void deletePlayer(int id);
}
