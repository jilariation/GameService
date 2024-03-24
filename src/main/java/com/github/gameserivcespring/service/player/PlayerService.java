package com.github.gameserivcespring.service.player;


import com.github.gameserivcespring.repository.player.dto.PlayerDTO;
import com.github.gameserivcespring.repository.player.entity.Player;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Сервис для {@link Player}
 */
public interface PlayerService {
    /**
     * Регистрирует игрока {@link Player}
     * @return Возвращает {@link Player}
     */
    Player save(Player player);

    /**
     * Обновляет даные об игроке
     * @param value Значение транзакции
     * @param player Игрок, который соверашает транзакцию
     */
    void update(int value, Player player);

    /**
     * Создает игрока
     * @param player Сущность игрока
     * @return Возвращает {@link Player}
     */
    Player create(Player player);

    /**
     * Удаляет игрока по его ID
     * @param id ID игрока
     */
    void deletePlayer(int id);

    /**
     * Возвращает игрока по его имени
     * @param username Имя игрока
     * @return Возращает {@link Player}
     */
    Player getByUsername(String username);

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     * @return Возвращает {@link UserDetailsService}
     */
    UserDetailsService userDetailsService();

    /**
     * Находит игрока по его почте
     * @param mail Почта игрока
     * @return Возвращает {@link Player}
     */
    Player findByMail(String mail);

    /**
     * Находит игрока по его ID
     * @param id ID игрока
     * @return Возращает {@link Player}
     */
    Player findById(int id);

    /**
     * Получение текущего игрока
     * @return Возращает {@link Player}
     */
    Player getCurrentUser();

    /**
     * Преобразует {@link Player} в {@link PlayerDTO}
     * @param player Игрок, которого нужно преобразовать
     * @return Возвращает DTO игрока
     */
    PlayerDTO convertToPlayerDTO(Player player);
}
