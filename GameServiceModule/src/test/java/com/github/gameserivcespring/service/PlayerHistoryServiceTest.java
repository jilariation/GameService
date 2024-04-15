package com.github.gameserivcespring.service;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Unit-testing for PlayerHistoryService")
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerHistoryServiceTest {
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
//    @Mock
//    private PlayerHistoryRepository playerHistoryRepository;
//    @InjectMocks
//    private PlayerHistoryServiceImpl playerHistoryService;
//
//    @Test
//    void testSavePlayerHistory() {
//        //Arrange
//        Player player = new Player(
//                1,
//                "example@mail.com",
//                "123456",
//                100
//        );
//        PlayerHistory playerHistory = new PlayerHistory(
//                player,
//                PlayerHistoryEnum.REG.name()
//        );
//
//        //Act
//        playerHistoryService.save(playerHistory);
//
//        //Assert
//        verify(playerHistoryRepository, times(1)).save(playerHistory);
//    }
//
//    @Test
//    void testFindAllByPlayer() {
//        //Arrange
//        Player player = new Player(
//                1,
//                "example@mail.com",
//                "123456",
//                100
//        );
//
//        //Act
//        playerHistoryService.findAllByPlayer(player);
//
//        //Assert
//        verify(playerHistoryRepository, times(1)).findAllByPlayer(player);
//    }
}
