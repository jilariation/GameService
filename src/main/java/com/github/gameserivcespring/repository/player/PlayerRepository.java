package com.github.gameserivcespring.repository.player;

import com.github.gameserivcespring.repository.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для {@link Player}
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
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
     * Находит игрока по его имени
     * @param username Имя игрока
     * @return Возвращает игрока
     */
    Player findByUsername(String username);

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
}
