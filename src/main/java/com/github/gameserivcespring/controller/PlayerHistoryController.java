package com.github.gameserivcespring.controller;

import com.github.gameserivcespring.repository.dto.PlayerHistoryDTO;
import com.github.gameserivcespring.repository.dto.PlayerHistoryResponse;
import com.github.gameserivcespring.repository.entity.PlayerHistory;
import com.github.gameserivcespring.service.player.PlayerHistoryService;
import com.github.gameserivcespring.service.player.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/player_history")
@RestController
public class PlayerHistoryController {
    private final ModelMapper modelMapper;
    private final PlayerHistoryService playerHistoryService;
    private final PlayerService playerService;

    @Autowired
    public PlayerHistoryController(ModelMapper modelMapper, PlayerHistoryService playerHistoryService,
                                   PlayerService playerService) {
        this.modelMapper = modelMapper;
        this.playerHistoryService = playerHistoryService;
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public List<PlayerHistoryResponse> getPlayerHistory(@PathVariable int id) {
        List<PlayerHistoryDTO> playerHistoryDTOList = playerHistoryService.findAllByPlayer(playerService.findById(id)
                        .get()).stream().map(this::convertToPlayerHistoryDTO).toList();
        List<PlayerHistoryResponse> playerHistoryResponseList = new ArrayList<>();
        for(PlayerHistoryDTO playerHistoryDTO : playerHistoryDTOList) {
            playerHistoryResponseList.add(new PlayerHistoryResponse(
                    playerHistoryDTO.getPlayer().getId(),
                    playerHistoryDTO.getWhatPlayerDoing()
            ));
        }
        return playerHistoryResponseList;
    }

    private PlayerHistoryDTO convertToPlayerHistoryDTO(PlayerHistory playerHistory) {
        return modelMapper.map(playerHistory, PlayerHistoryDTO.class);
    }
}
