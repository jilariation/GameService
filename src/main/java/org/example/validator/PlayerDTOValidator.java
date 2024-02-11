package org.example.validator;

import jakarta.validation.*;
import org.example.repository.dto.PlayerDTO;

import java.util.Set;

public class PlayerDTOValidator {
    public void validate(PlayerDTO playerDTO) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<PlayerDTO>> violations = validator.validate(playerDTO);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<PlayerDTO> violation : violations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("PlayerDto is valid");
        }
    }
}
