package com.github.gameserivcespring.repository.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    @NotNull
    private Integer id;

    @NotNull
    @Min(1)
    private Integer value;

    @NotNull
    private String transactionType;
}
