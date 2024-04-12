package com.github.gameserivcespring.userservice.service;

import com.github.gameserivcespring.userservice.repository.PlayerRepository;
import com.github.gameserivcespring.userservice.repository.dto.UserDTO;
import com.github.gameserivcespring.userservice.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Override
    public User save(User user) {
        playerRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void update(int value, User user) {
        user.setBalance(user.getBalance() + value);
        playerRepository.save(user);
    }

    @Override
    @Transactional
    public User create(User user) {
        if (playerRepository.existsByUsername(user.getUsername())) {
                throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (playerRepository.existsByMail(user.getMail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByMail(String mail) {
        return playerRepository.findByMail(mail);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(int id) {
        return playerRepository.findById(id);
    }

    @Override
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Override
    public UserDTO convertToPlayerDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }

    @Override
    public User getByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
}
