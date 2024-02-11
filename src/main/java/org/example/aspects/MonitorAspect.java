package org.example.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.repository.entity.PlayerHistory;
import org.example.service.PlayerService;
import org.example.service.PlayerServiceImpl;

@Aspect
public class MonitorAspect {
    private final PlayerService playerService = new PlayerServiceImpl();

    @Pointcut("within(@org.example.aspects.annotations.Monitor *)")
    public void beanAnnotatedWithMonitor() {}
    @Pointcut("execution(protected * *(..))")
    public void publicMethod() {}
    @Pointcut("publicMethod() && beanAnnotatedWithMonitor()")
    public void publicMethodInsideAClassMarkedWithAtMonitor() {}

    @Around("publicMethodInsideAClassMarkedWithAtMonitor()")
    public Object addToPlayerHistory(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        String servletName = joinPoint.getTarget().getClass().getSimpleName();
        Integer id = playerService.getPlayer().getId();
        switch (servletName) {
            case "CreditTransactionServlet" -> playerService.addEnumToDatabase(id, PlayerHistory.CREDIT);
            case "DebitTransactionServlet" -> playerService.addEnumToDatabase(id, PlayerHistory.DEBIT);
            case "LoginServlet" -> playerService.addEnumToDatabase(id, PlayerHistory.LOG);
            case "RegistrationServlet" -> playerService.addEnumToDatabase(id, PlayerHistory.REG);
            case "TransactionHistoryServlet" -> playerService.addEnumToDatabase(id, PlayerHistory.TRANSACTION_HISTORY);
            case "PlayerInformationServlet" -> playerService.addEnumToDatabase(id, PlayerHistory.INFO);
        }
        return result;
    }
}
