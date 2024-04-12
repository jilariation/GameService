package com.github.gameserivcespring.userservice.service;


import com.github.gameserivcespring.userservice.repository.dto.UserDTO;
import com.github.gameserivcespring.userservice.repository.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Сервис для {@link User}
 */
public interface PlayerService {
    /**
     * Регистрирует игрока {@link User}
     * @return Возвращает {@link User}
     */
    User save(User user);

    /**
     * Обновляет даные об игроке
     * @param value Значение транзакции
     * @param user Игрок, который соверашает транзакцию
     */
    void update(int value, User user);

    /**
     * Создает игрока
     * @param user Сущность игрока
     * @return Возвращает {@link User}
     */
    User create(User user);

    /**
     * Удаляет игрока по его ID
     * @param id ID игрока
     */
    void deletePlayer(int id);

    /**
     * Возвращает игрока по его имени
     * @param username Имя игрока
     * @return Возращает {@link User}
     */
    User getByUsername(String username);

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
     * @return Возвращает {@link User}
     */
    User findByMail(String mail);

    /**
     * Находит игрока по его ID
     * @param id ID игрока
     * @return Возращает {@link User}
     */
    User findById(int id);

    /**
     * Получение текущего игрока
     * @return Возращает {@link User}
     */
    User getCurrentUser();

    /**
     * Преобразует {@link User} в {@link UserDTO}
     * @param user Игрок, которого нужно преобразовать
     * @return Возвращает DTO игрока
     */
    UserDTO convertToPlayerDTO(User user);
}
