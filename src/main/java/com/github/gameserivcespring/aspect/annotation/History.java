package com.github.gameserivcespring.aspect.annotation;

import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.player.entity.PlayerHistoryEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface History {
    PlayerHistoryEnum typeOfHistory() default PlayerHistoryEnum.AUDIT;
}
