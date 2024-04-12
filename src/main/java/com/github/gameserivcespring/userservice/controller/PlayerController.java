package com.github.gameserivcespring.userservice.controller;

import com.github.gameserivcespring.aspect.annotation.History;
import com.github.gameserivcespring.aspect.annotation.Logging;
import com.github.gameserivcespring.userservice.repository.dto.UserDTO;
import com.github.gameserivcespring.userservice.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/about")
    public ResponseEntity<UserDTO> getPlayer() {
        return ResponseEntity.ok(playerService.convertToPlayerDTO(playerService.getByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName())));
    }
}
