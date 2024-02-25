package com.github.gameserivcespring.repository.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;

import lombok.Setter;
@Schema(description = "Сущность игрока")
@Getter
@Setter
public class PlayerDTO {
    @Schema(description = "Почта игрока", example = "example@mail.com")
    @NotEmpty
    @Size(min = 2, max = 100, message = "Mail is incorrect")
    @Email
    private String mail;

    @Schema(description = "Пароль игрока", example = "123456")
    @NotEmpty
    @Size(min = 5, max = 100, message = "Password is incorrect")
    private String password;

    @Schema(description = "Баланс игрока", example = "100")
    @Min(0)
    @NotNull
    private Integer balance = 100;
}
