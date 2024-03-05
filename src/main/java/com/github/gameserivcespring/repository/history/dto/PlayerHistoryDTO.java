package com.github.gameserivcespring.repository.history.dto;

import com.github.gameserivcespring.repository.player.entity.Player;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "DTO истории игрока")
@Getter
@Setter
public class PlayerHistoryDTO {
    @Schema(description = "Сам игрок", example = "player")
    @NotNull
    private Player player;

    @Schema(description = "Операция, которую совершил игрок", example = "REG")
    @NotNull
    private String whatPlayerDoing;
}
