package com.github.gameserivcespring.userservice.repository;

import com.github.gameserivcespring.userservice.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для {@link User}
 */
@Repository
public interface PlayerRepository extends JpaRepository<User, Integer> {
    /**
     * Находит игрока с таким логином и паролем
     * @param mail Логиг(почта) игрока
     * @param password Пароль игрока
     * @return Возвращает либо игрока, либо null
     */
    User findPlayerByMailAndPassword(String mail, String password);

    /**
     * Находит игрока по его почте
     * @param mail Почта игрока
     * @return Возвращает либо игрока, либо null
     */
    User findByMail(String mail);

    /**
     * Находит игрока по его ID
     * @param id ID игрока
     * @return Возращает либо игрока, либо null
     */
    Optional<User> findById(UUID id);

    /**
     * Находит игрока по его имени
     * @param username Имя игрока
     * @return Возвращает игрока
     */
    User findByUsername(String username);

    /**
     * Проверяет существование игрока по его имени
     * @param username Имя игрока
     * @return Возвращает true или false в зависимости от результата
     */
    boolean existsByUsername(String username);

    /**
     * Проверяет существование игрока по его почте
     * @param mail Почта игрока
     * @return Возвращает true или false в зависимости от результата
     */
    boolean existsByMail(String mail);

    /**
     * Удаляет пользователя по его ID
     * @param id ID пользователя
     */
    void deleteById(UUID id);
}