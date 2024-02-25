package com.github.gameserivcespring.errors.player;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PlayerErrorResponse {
    @Schema(name = "Текст оишибки")
    private String message;

    @Schema(name = "Время возникнования ошибки")
    private long timestamp;
}
