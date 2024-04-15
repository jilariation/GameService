package com.github.gameserivcespring.repository;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Unit-testing for PlayerRepository")
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerRepositoryTest {
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
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    @Test
//    void testFindPlayerByMailAndPassword_Exists() {
//        //Arrange
//        Player player = new Player(
//                1,
//                "example@mail.com",
//                "123456",
//                100
//        );
//        //Act
//        Player result = playerRepository.findPlayerByMailAndPassword(player.getMail(),player.getPassword());
//        //Assert
//        assertEquals(player.getId(), result.getId());
//        assertEquals(player.getMail(), result.getMail());
//        assertEquals(player.getPassword(), result.getPassword());
//        assertEquals(player.getBalance(), result.getBalance());
//    }
//
//    @Test
//    void testFindMyMail_Exists() {
//        //Arrange
//        Player player = new Player(
//                1,
//                "example@mail.com",
//                "123456",
//                100
//        );
//        //Act
//        Player result = playerRepository.findByMail(player.getMail());
//        //Assert
//        assertEquals(player.getMail(), result.getMail());
//    }
}
