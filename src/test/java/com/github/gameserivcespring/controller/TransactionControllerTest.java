package com.github.gameserivcespring.controller;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Unit-testing for TransactionController")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionControllerTest {

//    @LocalServerPort
//    private Integer port;
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
//    @BeforeEach
//    void setUp() {
//        RestAssured.baseURI = "http://localhost:" + port;
//    }
//
//    @InjectMocks
//    private TransactionController transactionController;
//    @Mock
//    private PlayerService playerService;
//    @Mock
//    private TransactionService transactionService;
//    @Mock
//    private PlayerHistoryService playerHistoryService;
//    @Mock
//    private TransactionDebitValidator transactionDebitValidator;
//    @Mock
//    private TransactionCreditValidator transactionCreditValidator;
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Test
//    void testDebit_Success() {
//        // Arrange
//        TransactionDTO transactionDTO = new TransactionDTO();
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.hasErrors()).thenReturn(false);
//
//        //Mocks
//        Player player = new Player(
//                1,
//                "example@mail.com",
//                "123456",
//                100
//        );
//        Transaction transaction = new Transaction(
//                1,
//                50,
//                "DEBIT",
//                player
//        );
//        transaction.setPlayer(player);
//        when(modelMapper.map(transactionDTO, Transaction.class)).thenReturn(transaction);
//        when(playerService.findById(player.getId())).thenReturn(player);
//        when(transactionDebitValidator.supports(any())).thenReturn(true);
//        doNothing().when(transactionDebitValidator).validate(transaction, bindingResult);
//
//        // Act
//        ResponseEntity<HttpStatus> response = transactionController.debit(transactionDTO, 1, bindingResult);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(50, player.getBalance());
//        verify(playerService, times(1)).findById(player.getId());
//        verify(transactionDebitValidator, times(1)).validate(transaction, bindingResult);
//        verify(transactionService, times(1)).save(transaction);
//        verify(playerHistoryService, times(1)).save(any(PlayerHistory.class));
//    }
//
//    @Test
//    void testCredit_Success() {
//        // Arrange
//        TransactionDTO transactionDTO = new TransactionDTO();
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.hasErrors()).thenReturn(false);
//
//        //Mocks
//        Player player = new Player(
//                1,
//                "example@mail.com",
//                "123456",
//                100
//        );
//        Transaction transaction = new Transaction(
//                2,
//                50,
//                "CREDIT",
//                player
//        );
//        transaction.setPlayer(player);
//        when(modelMapper.map(transactionDTO, Transaction.class)).thenReturn(transaction);
//        when(playerService.findById(player.getId())).thenReturn(player);
//        when(transactionCreditValidator.supports(any())).thenReturn(true);
//        doNothing().when(transactionCreditValidator).validate(transaction, bindingResult);
//
//        // Act
//        ResponseEntity<HttpStatus> response = transactionController.credit(transactionDTO, 1, bindingResult);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(150, player.getBalance());
//        verify(playerService, times(1)).findById(player.getId());
//        verify(transactionCreditValidator, times(1)).validate(transaction, bindingResult);
//        verify(transactionService, times(1)).save(transaction);
//        verify(playerHistoryService, times(1)).save(any(PlayerHistory.class));
//    }
}
