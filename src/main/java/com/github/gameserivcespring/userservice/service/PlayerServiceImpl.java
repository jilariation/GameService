package com.github.gameserivcespring.userservice.service;

import com.github.gameserivcespring.userservice.automation.AbstractService;
import com.github.gameserivcespring.userservice.repository.PlayerRepository;
import com.github.gameserivcespring.userservice.repository.dto.UserDTO;
import com.github.gameserivcespring.userservice.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl extends AbstractService<User, UserDTO, UUID> implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public User save(User model) {
        if (playerRepository.existsByUsername(model.getUsername())) {
                throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (playerRepository.existsByMail(model.getMail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        playerRepository.save(model);
        return model;
    }

    @Override
    @Transactional
    public Optional<User> getModel(UUID id) {
        return Optional.ofNullable(playerRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ));
    }

    @Override
    @Transactional
    public User updateModel(User updateModel) {
        return playerRepository.save(updateModel);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        playerRepository.deleteById(id);
    }

    @Override
    public final User convertToModel(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public final UserDTO convertToDTO(User model) {
        return modelMapper.map(model, UserDTO.class);
    }

    @Override
    public final User findByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    @Override
    public final UserDetailsService userDetailsService() {
        return this::findByUsername;
    }

    @Override
    public final User findByMail(String mail) {
        return playerRepository.findByMail(mail);
    }
}