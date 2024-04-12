package com.github.gameserivcespring.notificationservice.repository;

import com.github.gameserivcespring.userservice.repository.entity.User;
import com.github.gameserivcespring.notificationservice.repository.entity.PlayerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для {@link PlayerHistory}
 */
@Repository
public interface PlayerHistoryRepository extends JpaRepository<PlayerHistory, Integer> {
    /**
     * Находит все операции для {@link User}
     * @param user Игрок, который совершал операции
     * @return Возвращает историю игрока
     */
    List<PlayerHistory> findAllByUser(User user);
}
