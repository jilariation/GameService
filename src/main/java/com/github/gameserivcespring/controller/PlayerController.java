package com.github.gameserivcespring.controller;

import com.github.gameserivcespring.errors.player.PlayerErrorResponse;
import com.github.gameserivcespring.errors.player.PlayerException;
import com.github.gameserivcespring.repository.dto.PlayerDTO;
import com.github.gameserivcespring.repository.entity.Player;
import com.github.gameserivcespring.repository.entity.PlayerHistory;
import com.github.gameserivcespring.repository.entity.PlayerHistoryEnum;
import com.github.gameserivcespring.service.player.PlayerHistoryService;
import com.github.gameserivcespring.service.player.PlayerService;
import com.github.gameserivcespring.validator.player.PlayerLogValidator;
import com.github.gameserivcespring.validator.player.PlayerRegValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.github.gameserivcespring.errors.ErrorsUtil.returnErrorsToClient;

@Tag(name = "Контроллер игрока",
        description = "Контроллер для взаимодействия с игроком")
@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerHistoryService playerHistoryService;
    private final PlayerRegValidator playerRegValidator;
    private final PlayerLogValidator playerLogValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerHistoryService playerHistoryService,
                            PlayerRegValidator playerRegValidator, PlayerLogValidator playerLogValidator,
                            ModelMapper modelMapper) {
        this.playerService = playerService;
        this.playerHistoryService = playerHistoryService;
        this.playerRegValidator = playerRegValidator;
        this.playerLogValidator = playerLogValidator;
        this.modelMapper = modelMapper;
    }

    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистровать пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }) })
    @PostMapping("/reg")
    public ResponseEntity<HttpStatus> reg(
            @Parameter(description = "DTO игрока") @RequestBody @Valid PlayerDTO playerDTO,
            BindingResult bindingResult) {
        Player player = convertToPlayer(playerDTO);
        playerRegValidator.validate(player, bindingResult);
        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);
        playerService.save(player);
        playerHistoryService.save(new PlayerHistory(player, PlayerHistoryEnum.REG.name()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет авторизовать пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }) })
    @PostMapping("/log")
    public ResponseEntity<HttpStatus> log(
            @Parameter(description = "DTO игрока", required = true)@RequestBody @Valid PlayerDTO playerDTO,
            BindingResult bindingResult) {
        Player player = playerService.findPlayerByMailAndPassword(playerDTO.getMail(), playerDTO.getPassword());
        playerLogValidator.validate(player, bindingResult);
        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);
        playerService.loginPlayer(player.getMail(), player.getPassword());
        playerHistoryService.save(new PlayerHistory(player, PlayerHistoryEnum.LOG.name()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Выводит информацию об пользователе",
            description = "Выводит DTO пользователя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }) })
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayer(
            @Parameter(description = "ID игрока")@PathVariable int id) {
        Player player = playerService.findById(id);
        playerHistoryService.save(new PlayerHistory(player, PlayerHistoryEnum.INFO.name()));
        return ResponseEntity.ok(convertToPlayerDTO(player));
    }

    @Operation(
            summary = "Удаляет игрока",
            description = "Удаляет игрока по его ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = PlayerErrorResponse.class),
                    mediaType = "application/json") }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PlayerErrorResponse> handleException(PlayerException e) {
        PlayerErrorResponse response = new PlayerErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private Player convertToPlayer(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }
    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }
}
