package org.example.repository.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    @NotEmpty
    @Size(min = 5, max = 50, message = "The name is incorrect")
    private String name;

    @NotEmpty
    @Size(min = 1, max = 100, message = "The password is incorrect")
    private String password;

    private Integer balance;
}
