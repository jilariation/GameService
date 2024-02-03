package org.example.repository;

import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerRepository {
    private static List<Player> players = new ArrayList<>();
    private static Player player;
    public int registrationPlayer(String name) {
        int id = new Random().nextInt(100) + 1;
        Player player1 = new Player(
                id,
                name,
                100,
                new ArrayList<>(),
                new ArrayList<>());
        players.add(player1);
        player1.getPlayerHistory().add(PlayerHistory.REG);
        return id;
    }
    public void loginPlayer(int id) {
        for (Player player1 : players)
            if (player1.getId() == id) {
                player = player1;
                player.getPlayerHistory().add(PlayerHistory.LOG);
            }
    }
    public void getInformationAboutPlayer() {
        System.out.println(
                "Id: " + player.getId() + "\n" +
                "Name: " + player.getName() + "\n" +
                "Balance: " + player.getBalance() + "\n"
        );
        player.getPlayerHistory().add(PlayerHistory.INFO);
    }
    public void getPlayerHistory() {
        player.getPlayerHistory().add(PlayerHistory.AUDIT);
        for(PlayerHistory playerHistory : player.getPlayerHistory())
            System.out.println(playerHistory + ", ");
    }
    public static List<Player> getPlayers() {
        return players;
    }
    public static void setPlayers(List<Player> players) {
        PlayerRepository.players = players;
    }
    public static Player getPlayer() {
        return player;
    }
    public static void setPlayer(Player player) {
        PlayerRepository.player = player;
    }
}
