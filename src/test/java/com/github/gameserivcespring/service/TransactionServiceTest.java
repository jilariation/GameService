package com.github.gameserivcespring.service;

import com.github.gameserivcespring.repository.transaction.TransactionRepository;
import com.github.gameserivcespring.repository.player.entity.Player;
import com.github.gameserivcespring.repository.transaction.entity.Transaction;
import com.github.gameserivcespring.service.transaction.TransactionServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Unit-testing for TransactionService")
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionServiceTest {
//    static Transaction transaction;
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
//            "postgres:15-alpine"
//    );
//
//    @BeforeAll
//    static void beforeAll() {
//        postgres.start();
//    }
//
//    @AfterAll
//    static void afterAll() {
//        postgres.stop();
//    }
//
//    @DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgres::getJdbcUrl);
//        registry.add("spring.datasource.username", postgres::getUsername);
//        registry.add("spring.datasource.password", postgres::getPassword);
//    }
//
//    @Mock
//    private TransactionRepository transactionRepository;
//    @InjectMocks
//    private TransactionServiceImpl transactionService;
//
//
//    @Test
//    void testSave() {
//        //Arrange
//        Transaction testTransaction = transaction;
//        //Act
//        transactionService.save(testTransaction);
//        //Assert
//        verify(transactionRepository, times(1)).save(testTransaction);
//    }
//
//    @Test
//    void testFindById() {
//        //Arrange
//        Transaction testTransaction = new Transaction(
//                1,
//                10,
//                "DEBIT",
//                new Player(
//                        2,
//                        "ivan@mail.com",
//                        "password",
//                        100
//                )
//        );;
//        //Act
//        transactionService.findById(testTransaction.getId());
//        //Assert
//        verify(transactionRepository, times(1)).findById(testTransaction.getId());
//    }
}
