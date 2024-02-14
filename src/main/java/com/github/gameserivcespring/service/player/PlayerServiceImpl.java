package com.github.gameserivcespring.service.player;

import com.github.gameserivcespring.repository.PlayerRepository;
import com.github.gameserivcespring.repository.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void update(Player updatedPlayer,int id) {
        Player player =  playerRepository.findById(id).get();
        player.setName(player.getName());
        player.setPassword(player.getPassword());
        player.setBalance(player.getBalance());
        playerRepository.save(player);
    }

    @Override
    public Optional<Player> loginPlayer(String name, String password) {
        return playerRepository.findPlayerByNameAndPassword(name, password);
    }

    @Override
    public Optional<Player> findPlayerByNameAndPassword(String name, String password) {
        return playerRepository.findPlayerByNameAndPassword(name, password);
    }

    @Override
    public Optional<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }

    @Override
    public Optional<Player> findById(int id) {
        return playerRepository.findById(id);
    }
}
