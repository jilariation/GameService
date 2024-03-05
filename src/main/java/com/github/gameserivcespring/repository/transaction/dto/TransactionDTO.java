package com.github.gameserivcespring.repository.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
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
    @NotNull
    @Min(1)
    private Integer value;

    @Schema(description = "Тип транзакции", example = "DEBIT")
    @NotNull
    private String transactionType;
}
