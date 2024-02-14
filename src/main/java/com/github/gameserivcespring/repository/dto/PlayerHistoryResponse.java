package com.github.gameserivcespring.repository.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerHistoryResponse {
    @NotNull
    private Integer id;
    @NotNull
    private String whatPlayerDoing;

}
