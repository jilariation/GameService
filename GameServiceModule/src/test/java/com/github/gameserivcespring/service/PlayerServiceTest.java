package com.github.gameserivcespring.service;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Unit-testing for PlayerService")
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerServiceTest {
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
//    private PlayerRepository playerRepository;
//    @InjectMocks
//    private PlayerServiceImpl playerService;
//
//    @Test
//    void testSave(){
//        //Arrange
//        Player testPlayer = new Player(
//                1,
//                "ivan@mail.com",
//                "password",
//                100);;
//        //Act
//        playerService.save(testPlayer);
//        //Assert
//        verify(playerRepository, times(1)).save(testPlayer);
//    }
//    @Test
//    void testLoginPlayer() {
//        //Arrange
//        Player testPlayer = new Player(
//                1,
//                "ivan@mail.com",
//                "password",
//                100);;
//
//        //Act
//        playerService.loginPlayer(testPlayer.getMail(), testPlayer.getPassword());
//
//        //Assert
//        verify(playerRepository, times(1))
//                .findPlayerByMailAndPassword(testPlayer.getMail(), testPlayer.getPassword());
//    }
//
//    @Test
//    void testFindByMail() {
//        //Arrange
//        Player testPlayer = new Player(
//                1,
//                "ivan@mail.com",
//                "password",
//                100);;
//        //Act
//        playerService.findByMail(testPlayer.getMail());
//        //Assert
//        verify(playerRepository, times(1)).findByMail(testPlayer.getMail());
//    }
//
//    @Test
//    void testFindById() {
//        //Act
//        playerService.findById(1);
//        //Assert
//        verify(playerRepository, times(1)).findById(1);
//    }
}
