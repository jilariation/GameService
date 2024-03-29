package com.github.gameserivcespring.repository.history;

import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.history.entity.PlayerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для {@link PlayerHistory}
 */
@Repository
public interface PlayerHistoryRepository extends JpaRepository<PlayerHistory, Integer> {
    /**
     * Находит все операции для {@link Player}
     * @param player Игрок, который совершал операции
     * @return Возвращает историю игрока
     */
    List<PlayerHistory> findAllByPlayer(Player player);
}
