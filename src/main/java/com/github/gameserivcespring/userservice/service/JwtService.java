package com.github.gameserivcespring.userservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Сервис для работы с JWT
 */
public interface JwtService {
    /**
     * Извлечение имени пользователя из токена
     *
     * @param token токен
     * @return имя пользователя
     */
    String extractUserName(String token);

    /**
     * Генерация токена
     *
     * @param userDetails данные пользователя
     * @return токен
     */
    String generateToken(UserDetails userDetails);

    /**
     * Проверка токена на валидность
     *
     * @param token       токен
     * @param userDetails данные пользователя
     * @return true, если токен валиден
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Извлечение данных из токена
     *
     * @param token           токен
     * @param claimsResolvers функция извлечения данных
     * @param <T>             тип данных
     * @return данные
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolvers);

    /**
     * Генерация токена
     *
     * @param extraClaims дополнительные данные
     * @param userDetails данные пользователя
     * @return токен
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Проверка токена на просроченность
     *
     * @param token токен
     * @return true, если токен просрочен
     */
    boolean isTokenExpired(String token);

    /**
     * Извлечение даты истечения токена
     *
     * @param token токен
     * @return дата истечения
     */
    Date extractExpiration(String token);

    /**
     * Извлечение всех данных из токена
     *
     * @param token токен
     * @return данные
     */
    Claims extractAllClaims(String token);

    /**
     * Получение ключа для подписи токена
     *
     * @return ключ
     */
    Key getSigningKey();
}
