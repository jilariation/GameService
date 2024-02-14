package com.github.gameserivcespring.controller;

import com.github.gameserivcespring.errors.PlayerErrorResponse;
import com.github.gameserivcespring.errors.PlayerException;
import com.github.gameserivcespring.repository.dto.PlayerDTO;
import com.github.gameserivcespring.repository.entity.Player;
import com.github.gameserivcespring.repository.entity.PlayerHistory;
import com.github.gameserivcespring.repository.entity.PlayerHistoryEnum;
import com.github.gameserivcespring.service.player.PlayerHistoryService;
import com.github.gameserivcespring.service.player.PlayerService;
import com.github.gameserivcespring.validator.player.PlayerLogValidator;
import com.github.gameserivcespring.validator.player.PlayerRegValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.github.gameserivcespring.errors.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerHistoryService playerHistoryService;
    private final PlayerRegValidator playerRegValidator;
    private final PlayerLogValidator playerLogValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerHistoryService playerHistoryService, PlayerRegValidator playerRegValidator,
                            PlayerLogValidator playerLogValidator, ModelMapper modelMapper) {
        this.playerService = playerService;
        this.playerHistoryService = playerHistoryService;
        this.playerRegValidator = playerRegValidator;
        this.playerLogValidator = playerLogValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/reg")
    public ResponseEntity<HttpStatus> reg(@RequestBody @Valid PlayerDTO playerDTO, BindingResult bindingResult) {
        Player player = convertToPlayer(playerDTO);
        playerRegValidator.validate(player, bindingResult);
        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);
        playerService.save(player);
        playerHistoryService.save(new PlayerHistory(player, PlayerHistoryEnum.REG.name()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("log")
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid PlayerDTO playerDTO, BindingResult bindingResult) {
        Player player = playerService.findPlayerByNameAndPassword(playerDTO.getName(), playerDTO.getPassword()).get();
        playerLogValidator.validate(player, bindingResult);
        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);
        playerService.loginPlayer(player.getName(), player.getPassword()).get();
        playerHistoryService.save(new PlayerHistory(player, PlayerHistoryEnum.LOG.name()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable int id) {
        Player player = playerService.findById(id).get();
        playerHistoryService.save(new PlayerHistory(player, PlayerHistoryEnum.INFO.name()));
        return ResponseEntity.ok(convertToPlayerDTO(player));
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
