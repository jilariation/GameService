package com.github.gameserivcespring.repository.transaction.dto;

import com.github.gameserivcespring.repository.player.entity.Player;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Сущность транзакции")
@Getter
@Setter
public class TransactionDTO {
    @Schema(description = "ID транзакции", example = "1")
    @NotNull
    private Integer id;

    @Schema(description = "Значение транзакции", example = "10")
    @Min(1)
    @NotBlank(message = "Значение не может быть пустыми")
    private Integer value;

    @Schema(description = "Тип транзакции", example = "DEBIT")
    @NotBlank(message = "Тип транзакции не может быть пустыми")
    private String transactionType;

    @Schema(description = "Игрок", example = "Player")
    private Player player;
}
