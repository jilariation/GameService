package com.github.gameserivcespring.notificationservice.service;

import com.github.gameserivcespring.notificationservice.repository.PlayerHistoryRepository;
import com.github.gameserivcespring.notificationservice.repository.dto.PlayerHistoryDTO;
import com.github.gameserivcespring.notificationservice.repository.dto.PlayerHistoryResponse;
import com.github.gameserivcespring.userservice.repository.PlayerRepository;
import com.github.gameserivcespring.notificationservice.repository.entity.PlayerHistory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerHistoryServiceImpl implements PlayerHistoryService {
    private final PlayerRepository playerRepository;
    private final PlayerHistoryRepository playerHistoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(PlayerHistory playerHistory) {
        playerHistoryRepository.save(playerHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerHistory> findAllByPlayer(User user) {
        return playerHistoryRepository.findAllByUser(user);
    }

    @Override
    public PlayerHistoryDTO convertToPlayerHistoryDTO(PlayerHistory playerHistory) {
        return modelMapper.map(playerHistory, PlayerHistoryDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerHistoryResponse> getAllPlayerHistory(int id) {

        List<PlayerHistoryDTO> playerHistoryDTOList = playerHistoryRepository.findAllByUser(playerRepository.findById(id))
                .stream().map(this::convertToPlayerHistoryDTO).toList();

        return playerHistoryDTOList.stream().map(playerHistoryDTO -> new PlayerHistoryResponse(
                playerHistoryDTO.getUser().getId(),
                playerHistoryDTO.getWhatPlayerDoing())).collect(Collectors.toList());
    }
}