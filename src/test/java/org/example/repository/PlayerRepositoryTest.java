package org.example.repository;

import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerRepositoryTest {
    private PlayerRepository playerRepository;

    @BeforeEach
    void init() {
        playerRepository = new PlayerRepository();
        List<Player> players = spy(new ArrayList<>());
        PlayerRepository.setPlayers(players);
    }

    @Test
    void shouldProperlyRegistrationPlayer() {
        //when
        int id = playerRepository.registrationPlayer("John");

        //then
        assertNotNull(PlayerRepository.getPlayers().get(0));
        assertEquals(id, PlayerRepository.getPlayers().get(0).getId());
        assertEquals("John", PlayerRepository.getPlayers().get(0).getName());
        assertEquals(100, PlayerRepository.getPlayers().get(0).getBalance());
        assertEquals(1, PlayerRepository.getPlayers().get(0).getPlayerHistory().size());
        assertTrue(PlayerRepository.getPlayers().get(0).getPlayerHistory().contains(PlayerHistory.REG));
    }

    @Test
    void shouldProperlyLoginPlayer() {
        //given
        Player player = new Player(1, "John", 100, new ArrayList<>(), new ArrayList<>());
        PlayerRepository.setPlayer(player);
        PlayerRepository.getPlayers().add(player);

        //when
        playerRepository.loginPlayer(1);

        //then
        assertNotNull(PlayerRepository.getPlayer());
        assertNotNull(PlayerRepository.getPlayers().get(0));
        assertTrue(PlayerRepository.getPlayer().getPlayerHistory().contains(PlayerHistory.LOG));
        assertEquals(PlayerRepository.getPlayer(), player);
    }

    @Test
    void shouldProperlyGetInformationAboutPlayer() {
        //given
        Player player = new Player(1, "John", 100, new ArrayList<>(), new ArrayList<>());
        PlayerRepository.setPlayer(player);

        //when
        playerRepository.getInformationAboutPlayer();

        //then
        assertNotNull(PlayerRepository.getPlayer());
        assertTrue(PlayerRepository.getPlayer().getPlayerHistory().contains(PlayerHistory.INFO));
    }

    @Test
    void shouldProperlyGetPlayerHistory() {
        //given
        Player player = new Player(1, "John", 100, new ArrayList<>(), new ArrayList<>());
        player.getPlayerHistory().add(PlayerHistory.REG);
        PlayerRepository.setPlayer(player);

        //when
        playerRepository.getPlayerHistory();

        //then
        assertTrue(PlayerRepository.getPlayer().getPlayerHistory().contains(PlayerHistory.AUDIT));
        assertTrue(PlayerRepository.getPlayer().getPlayerHistory().contains(PlayerHistory.REG));

    }
}
