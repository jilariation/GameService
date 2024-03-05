package com.github.gameserivcespring.controller;

import com.github.gameserivcespring.errors.player.PlayerErrorResponse;
import com.github.gameserivcespring.repository.history.dto.PlayerHistoryDTO;
import com.github.gameserivcespring.repository.history.dto.PlayerHistoryResponse;
import com.github.gameserivcespring.repository.history.entity.PlayerHistory;
import com.github.gameserivcespring.service.player.PlayerHistoryService;
import com.github.gameserivcespring.service.player.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Контроллер истории игрока",
        description = "Контроллер для контроля действий игрока")
@RequestMapping("/player_history")
@RestController
@RequiredArgsConstructor
public class PlayerHistoryController {
    private final ModelMapper modelMapper;
    private final PlayerHistoryService playerHistoryService;
    private final PlayerService playerService;

    @Operation(
            summary = "История игрока по его ID",
            description = "Выводит историю игрока по его ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }) })
    @GetMapping("/{id}")
    public List<PlayerHistoryResponse> getPlayerHistory(
            @Parameter(description = "ID игрока") @PathVariable int id) {
        List<PlayerHistoryDTO> playerHistoryDTOList = playerHistoryService.findAllByPlayer(playerService.findById(id))
                .stream().map(this::convertToPlayerHistoryDTO).toList();
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
