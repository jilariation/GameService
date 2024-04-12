package com.github.gameserivcespring.notificationservice.controller;

import com.github.gameserivcespring.userservice.error.PlayerErrorResponse;
import com.github.gameserivcespring.notificationservice.repository.dto.PlayerHistoryResponse;
import com.github.gameserivcespring.notificationservice.service.PlayerHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Контроллер истории игрока",
        description = "Контроллер для контроля действий игрока")
@RequestMapping("/player_history")
@RestController
@RequiredArgsConstructor
public class UserHistoryController {
    private final PlayerHistoryService playerHistoryService;

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
        return playerHistoryService.getAllPlayerHistory(id);
    }
}