package com.github.gameserivcespring.repository.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    @NotEmpty
    @Size(min = 2, max = 100, message = "Name is incorrect")
    private String name;

    @NotEmpty
    @Size(min = 5, max = 100, message = "Password is incorrect")
    private String password;

    @Min(0)
    @NotNull
    private Integer balance = 100;
}
