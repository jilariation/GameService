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
        Player player =  playerRepository.findById(id);
        player.setMail(player.getMail());
        player.setPassword(player.getPassword());
        player.setBalance(player.getBalance());
        playerRepository.save(player);
    }

    @Override
    public Player loginPlayer(String mail, String password) {
        return playerRepository.findPlayerByMailAndPassword(mail, password);
    }

    @Override
    public Player findPlayerByMailAndPassword(String name, String password) {
        return playerRepository.findPlayerByMailAndPassword(name, password);
    }

    @Override
    public Player findByMail(String mail) {
        return playerRepository.findByMail(mail);
    }

    @Override
    public Player findById(int id) {
        return playerRepository.findById(id);
    }

    @Override
    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }
}
