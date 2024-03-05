package com.github.gameserivcespring.service.security;

import com.github.gameserivcespring.repository.security.dto.SignInRequest;
import com.github.gameserivcespring.repository.security.dto.SignUpRequest;
import com.github.gameserivcespring.repository.security.dto.JwtAuthenticationResponse;

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
