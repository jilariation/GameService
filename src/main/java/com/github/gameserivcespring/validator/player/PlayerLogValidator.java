package com.github.gameserivcespring.validator.player;

import com.github.gameserivcespring.repository.entity.Player;
import com.github.gameserivcespring.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayerLogValidator implements Validator {
    private final PlayerService playerService;

    @Autowired
    public PlayerLogValidator(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Player player = (Player) target;
        if(playerService.findPlayerByNameAndPassword(player.getName(), player.getPassword()).isEmpty())
            errors.rejectValue("player", "A player with this password and login was not found");
    }
}
