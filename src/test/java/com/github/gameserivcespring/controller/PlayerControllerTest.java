package com.github.gameserivcespring.controller;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unit-testing for PlayerController")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerControllerTest {

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
//
//    }
//
//    @Mock
//    private PlayerService playerService;
//    @Mock
//    private PlayerHistoryService playerHistoryService;
//    @Mock
//    private PlayerRegValidator playerRegValidator;
//    @Mock
//    private PlayerLogValidator playerLogValidator;
//    @Mock
//    private ModelMapper modelMapper;
//    @InjectMocks
//    private PlayerController playerController;
//
//    @Test
//    public void testReg_SuccessfulRegistration() {
//        // Arrange
//        PlayerDTO playerDTO = new PlayerDTO();
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.hasErrors()).thenReturn(false);
//
//        // Mocks
//        Player player = new Player();
//        when(modelMapper.map(playerDTO, Player.class)).thenReturn(player);
//
//        // Act
//        ResponseEntity<HttpStatus> response = playerController.reg(playerDTO, bindingResult);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(playerService, times(1)).save(player);
//        verify(playerHistoryService, times(1)).save(any(PlayerHistory.class));
//    }
//
//    @Test
//    void testLog_Success() {
//        // Arrange
//        PlayerDTO playerDTO = new PlayerDTO();
//        playerDTO.setMail("test@example.com");
//        playerDTO.setPassword("password");
//
//        Player player = new Player();
//        player.setMail("test@example.com");
//        player.setPassword("password");
//
//        BindingResult bindingResult = mock(BindingResult.class);
//
//        when(playerService.findPlayerByMailAndPassword("test@example.com", "password")).thenReturn(player);
//        doNothing().when(playerLogValidator).validate(player, bindingResult);
//
//        // Act
//        ResponseEntity<HttpStatus> response = playerController.log(playerDTO, bindingResult);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(playerService, times(1)).loginPlayer("test@example.com", "password");
//        verify(playerHistoryService, times(1)).save(any(PlayerHistory.class));
//    }
//
//    @Test
//    public void testGetPlayer_SuccessfulGet() {
//        // Arrange
//        int playerId = 1;
//
//        // Mocks
//        Player player = new Player();
//        when(playerService.findById(playerId)).thenReturn(player);
//
//        // Act
////        ResponseEntity<PlayerDTO> response = playerController.getPlayer(playerId);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
}