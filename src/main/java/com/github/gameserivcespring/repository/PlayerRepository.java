package com.github.gameserivcespring.repository;

import com.github.gameserivcespring.repository.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
}
