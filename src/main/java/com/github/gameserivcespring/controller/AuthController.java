package com.github.gameserivcespring.controller;

import com.github.gameserivcespring.aspect.annotation.History;
import com.github.gameserivcespring.aspect.annotation.Logging;
import com.github.gameserivcespring.repository.player.entity.PlayerHistoryEnum;
import com.github.gameserivcespring.repository.security.dto.SignInRequest;
import com.github.gameserivcespring.repository.security.dto.SignUpRequest;
import com.github.gameserivcespring.repository.security.dto.JwtAuthenticationResponse;
import com.github.gameserivcespring.service.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @Logging
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @Logging
    @History(typeOfHistory = PlayerHistoryEnum.LOG)
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}