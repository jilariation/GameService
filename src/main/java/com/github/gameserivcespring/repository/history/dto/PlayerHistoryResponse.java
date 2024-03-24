package com.github.gameserivcespring.repository.history.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Класс для вывода истории игрока")
@Getter
@Setter
@AllArgsConstructor
public class PlayerHistoryResponse {
    @Schema(description = "ID игрока", example = "1")
    @NotNull
    private Integer id;

    @Schema(description = "Операция, которую совершил игрок", example = "REG")
    @NotNull
    private String whatPlayerDoing;
}
