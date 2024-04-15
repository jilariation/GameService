package com.github.gameserivcespring.userservice.service;


import com.github.gameserivcespring.userservice.repository.dto.UserDTO;
import com.github.gameserivcespring.userservice.repository.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Сервис для {@link User}
 */
public interface PlayerService {
    /**
     * Находит юзера по его имени
     * @param username Имя юзера
     * @return Возращает юзера
     */
    User findByUsername(String username);

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
}
