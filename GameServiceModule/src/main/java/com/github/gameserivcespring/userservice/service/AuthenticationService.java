package com.github.gameserivcespring.userservice.service;

import com.github.gameserivcespring.userservice.repository.dto.SignInRequest;
import com.github.gameserivcespring.userservice.repository.dto.SignUpRequest;
import com.github.gameserivcespring.userservice.repository.dto.JwtAuthenticationResponse;

/**
 * Сервис для ауентификации
 */
public interface AuthenticationService {
    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    JwtAuthenticationResponse signUp(SignUpRequest request);

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    JwtAuthenticationResponse signIn(SignInRequest request);
}
