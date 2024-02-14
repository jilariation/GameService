package com.github.gameserivcespring.repository.dto;

import com.github.gameserivcespring.repository.entity.Player;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerHistoryDTO {
    @NotNull
    private Player player;

    @NotNull
    private String whatPlayerDoing;
}
