package com.github.gameserivcespring.repository;

import com.github.gameserivcespring.repository.entity.Player;
import com.github.gameserivcespring.repository.entity.PlayerHistory;
import com.github.gameserivcespring.repository.entity.PlayerHistoryEnum;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-testing for PlayerHistoryRepository")
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerHistoryRepositoryTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    PlayerHistoryRepository playerHistoryRepository;

    @Test
    void findAllByPlayer_Exists() {
        //Arrange
        Player player = new Player(
                1,
                "example@mail.com",
                "123456",
                100
        );
        PlayerHistory playerHistory = new PlayerHistory(
                player,
                PlayerHistoryEnum.REG.name()
        );
        List<PlayerHistory> playerHistoryList = Collections.singletonList(playerHistory);

        //Act
        List<PlayerHistory> playerHistoryListFromRepository = playerHistoryRepository.findAllByPlayer(player);

        //Assert
        assertEquals(playerHistoryList.get(0).getPlayer().getId(),
                playerHistoryListFromRepository.get(0).getPlayer().getId());
        assertEquals(playerHistoryList.get(0).getWhatPlayerDoing(),
                playerHistoryListFromRepository.get(0).getWhatPlayerDoing());
    }
}
