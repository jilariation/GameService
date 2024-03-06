package com.github.gameserivcespring.controller;

import com.github.gameserivcespring.aspect.annotation.History;
import com.github.gameserivcespring.aspect.annotation.Logging;
import com.github.gameserivcespring.repository.player.dto.PlayerDTO;
import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.player.entity.PlayerHistoryEnum;
import com.github.gameserivcespring.service.player.PlayerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Контроллер игрока",
        description = "Контроллер для взаимодействия с игроком")
@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final ModelMapper modelMapper;

    @Logging
    @History(typeOfHistory = PlayerHistoryEnum.INFO)
    @GetMapping("/about")
    public ResponseEntity<PlayerDTO> getPlayer() {
        return ResponseEntity.ok(convertToPlayerDTO(playerService.getByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName()))
        );
    }

    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }
}
