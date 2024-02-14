package com.github.gameserivcespring.repository;

import com.github.gameserivcespring.repository.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findPlayerByNameAndPassword(String name, String password);
    Optional<Player> findByName(String name);
}
