package com.github.gameserivcespring.service.player;

import com.github.gameserivcespring.repository.history.PlayerHistoryRepository;
import com.github.gameserivcespring.repository.history.dto.PlayerHistoryDTO;
import com.github.gameserivcespring.repository.history.dto.PlayerHistoryResponse;
import com.github.gameserivcespring.repository.player.PlayerRepository;
import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.history.entity.PlayerHistory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PlayerHistory> findAllByPlayer(Player player) {
        return playerHistoryRepository.findAllByPlayer(player);
    }

    @Override
    public PlayerHistoryDTO convertToPlayerHistoryDTO(PlayerHistory playerHistory) {
        return modelMapper.map(playerHistory, PlayerHistoryDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerHistoryResponse> getAllPlayerHistory(int id) {
        List<PlayerHistoryDTO> playerHistoryDTOList = playerHistoryRepository.findAllByPlayer(playerRepository.findById(id))
                .stream().map(this::convertToPlayerHistoryDTO).toList();

        return playerHistoryDTOList.stream().map(playerHistoryDTO -> new PlayerHistoryResponse(
                playerHistoryDTO.getPlayer().id(),
                playerHistoryDTO.getWhatPlayerDoing())).collect(Collectors.toList());
    }
}