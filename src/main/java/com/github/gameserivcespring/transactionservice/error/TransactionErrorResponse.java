package com.github.gameserivcespring.transactionservice.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionErrorResponse {
    @Schema(name = "Текст оишибки")
    private String message;

    @Schema(name = "Время возникновения ошибки")
    private long timestamp;
}
