package com.github.gameserivcespring.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerErrorResponse {
    private String message;
    private long timestamp;
}
