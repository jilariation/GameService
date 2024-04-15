package com.github.gameserivcespring.notificationservice.repository.dto;

import com.github.gameserivcespring.userservice.repository.entity.User;
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
    private User user;

    @Schema(description = "Операция, которую совершил игрок", example = "REG")
    @NotNull
    private String whatPlayerDoing;
}