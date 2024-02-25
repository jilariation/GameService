package com.github.gameserivcespring.validator.player;

import com.github.gameserivcespring.repository.entity.Player;
import com.github.gameserivcespring.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Валидатор для {@link Player}, который проверяет правильность присылаемых почты и пароля
 */
@Component
public class PlayerRegValidator implements Validator {
    private final PlayerService playerService;

    @Autowired
    public PlayerRegValidator(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Player player = (Player) target;

        if(playerService.findByMail(player.getMail()) != null)
            errors.rejectValue("name", "There is a player with that mail");
    }
}
