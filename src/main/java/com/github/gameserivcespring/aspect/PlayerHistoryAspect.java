package com.github.gameserivcespring.aspect;

import com.github.gameserivcespring.aspect.annotation.History;
import com.github.gameserivcespring.aspect.annotation.Logging;
import com.github.gameserivcespring.repository.history.entity.PlayerHistory;
import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.player.entity.PlayerHistoryEnum;
import com.github.gameserivcespring.service.player.PlayerHistoryService;
import com.github.gameserivcespring.service.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class PlayerHistoryAspect {
    private final PlayerService playerService;
    private final PlayerHistoryService playerHistoryService;

    @Logging
    @AfterReturning(pointcut = "@annotation(historyAnnotation)")
    public void trackPlayerHistoryMethod(History historyAnnotation) {
        PlayerHistoryEnum typeOfHistory = historyAnnotation.typeOfHistory();
        Player player = getPlayer();
        PlayerHistory playerHistory = new PlayerHistory(player, typeOfHistory.name());
        playerHistoryService.save(playerHistory);
        System.out.println(playerHistory);
    }

    private Player getPlayer(){
        return playerService.getByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
    }
}
