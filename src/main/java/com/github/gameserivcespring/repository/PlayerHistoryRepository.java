package com.github.gameserivcespring.repository;

import com.github.gameserivcespring.repository.entity.Player;
import com.github.gameserivcespring.repository.entity.PlayerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerHistoryRepository extends JpaRepository<PlayerHistory, Integer> {
    List<PlayerHistory> findAllByPlayer(Player player);
}
