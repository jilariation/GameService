package org.example.console;

import org.example.service.PlayerService;
import org.example.service.PlayerServiceImpl;
import org.example.service.TransactionService;
import org.example.service.TransactionServiceImpl;

import java.util.Scanner;

/**
 * Implementation of {@link ConsoleInteraction} interface
 */
public class ConsoleInteractionImpl implements ConsoleInteraction {
    private static final Scanner scanner = new Scanner(System.in);
    private final PlayerService playerService = new PlayerServiceImpl();
    private final TransactionService transactionService = new TransactionServiceImpl();

    @Override
    public void startWindows() {
        System.out.println("Привет! Это консольная версия WalletService! \n" +
                "Выбери действие: \n" +
                "1) Регистрация (Если вы не зарегистрированы) \n" +
                "2) Вход \n");
        switch (scanner.nextInt()) {
            case 1 -> {
                registrationWindow();
                loginWindow();
            }
            case 2 -> loginWindow();
        }
        menuWindow();
    }
    @Override
    public void registrationWindow() {
        System.out.println("Для регистрации введите ваше имя: \n" );
        String name = scanner.next();
        System.out.println("Теперь придумайте пароль: ");
        String password = scanner.next();
        playerService.registrationPlayer(name, password);
    }
    @Override
    public void loginWindow() {
        System.out.println("Чтобы войти, сначала введите ваше имя: \n");
        String name = scanner.next();
        System.out.println("Теперь введите ваш пароль");
        String password = scanner.next();
        playerService.loginPlayer(name, password);
    }
    @Override
    public void debitWindow() {
        System.out.println("Для совершения дебетовой транзакции введите сумму дебита: ");
        int debit = scanner.nextInt();
        System.out.println("Теперь введите индентификатор для вашей транзакции: ");
        int debitId = scanner.nextInt();
        switch (transactionService.debitTransaction(debit, debitId)) {
            case 1 -> debitWindow();
            case 2, 0 -> menuWindow();
        }
    }
    @Override
    public void creditWindow() {
        System.out.println("Для совершения кредитовой транзакции введите сумму кредита: ");
        int credit = scanner.nextInt();
        System.out.println("Теперь введите индентификатор для вашей транзакции: ");
        int creditId = scanner.nextInt();
        switch (transactionService.creditTransaction(credit, creditId)) {
            case 1 -> creditWindow();
            case 0 -> menuWindow();
        }
    }
    @Override
    public void menuWindow() {
        System.out.println("Добро пожаловать, " + playerService.getPlayer().getName() + " Выбери действие: \n" +
                "1) Информация об игроке \n" +
                "2) Дебит \n" +
                "3) Кредит \n" +
                "4) История операций \n" +
                "5) Аудит \n");
        switch (scanner.nextInt()) {
            case 1 -> {
                playerService.getInformationAboutPlayer();
                menuWindow();
            }
            case 2 -> {
                debitWindow();
                menuWindow();
            }
            case 3 -> {
                creditWindow();
                menuWindow();
            }
            case 4 -> {
                transactionService.transactionHistory(playerService.getPlayer());
                menuWindow();
            }
            case 5 -> {
                playerService.getPlayerHistory();
                menuWindow();
            }
        }
    }
}