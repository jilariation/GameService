package com.github.gameserivcespring.service.player;

import com.github.gameserivcespring.repository.player.PlayerRepository;
import com.github.gameserivcespring.repository.player.dto.PlayerDTO;
import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.player.entity.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void update(int value, Player player) {
        player.setBalance(player.getBalance() + value);
        playerRepository.save(player);
    }

    @Override
    public Player create(Player player) {
        if (playerRepository.existsByUsername(player.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (playerRepository.existsByMail(player.getMail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(player);
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
    public Player getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Override
    public PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }

    @Override
    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Player getByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
}
