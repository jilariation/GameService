package com.github.gameserivcespring.controller;

import com.github.gameserivcespring.errors.transaction.TransactionErrorResponse;
import com.github.gameserivcespring.errors.transaction.TransactionException;
import com.github.gameserivcespring.repository.transaction.dto.TransactionDTO;
import com.github.gameserivcespring.repository.history.entity.PlayerHistory;
import com.github.gameserivcespring.repository.player.entity.PlayerHistoryEnum;
import com.github.gameserivcespring.repository.transaction.entity.Transaction;
import com.github.gameserivcespring.service.player.PlayerHistoryService;
import com.github.gameserivcespring.service.player.PlayerService;
import com.github.gameserivcespring.service.transaction.TransactionService;
import com.github.gameserivcespring.validator.transaction.TransactionCreditValidator;
import com.github.gameserivcespring.validator.transaction.TransactionDebitValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер транзакций",
        description = "Контроллер для управления транзауциями")
@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class TransactionController {
    private final PlayerService playerService;
    private final TransactionService transactionService;
    private final PlayerHistoryService playerHistoryService;
    private final TransactionDebitValidator transactionDebitValidator;
    private final TransactionCreditValidator transactionCreditValidator;
    private final ModelMapper modelMapper;

    @Operation(
            summary = "Операция дебита",
            description = "Операция дебита над игроком с его ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = TransactionErrorResponse.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = TransactionErrorResponse.class),
                    mediaType = "application/json") }) })
    @PostMapping("/{id}/transaction/debit")
    public ResponseEntity<HttpStatus> debit(
            @Parameter(description = "DTO транзакции") @RequestBody @Valid TransactionDTO transactionDTO,
            @Parameter(description = "ID игрока") @PathVariable int id,
            BindingResult bindingResult) {
        Transaction transaction = convertToTransaction(transactionDTO);
        transaction.setPlayer(playerService.findById(id));
        transactionDebitValidator.validate(transaction, bindingResult);
        transactionService.save(transaction);
        transaction.getPlayer().setBalance(transaction.getPlayer().getBalance() - transaction.getValue());
        playerService.update(transaction.getPlayer(), id);
        playerHistoryService.save(new PlayerHistory(transaction.getPlayer(), PlayerHistoryEnum.DEBIT.name()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Операция кредита",
            description = "Операция кредита над игроком с его ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = TransactionErrorResponse.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = TransactionErrorResponse.class),
                    mediaType = "application/json") }) })
    @PostMapping("/{id}/transaction/credit")
    public ResponseEntity<HttpStatus> credit(
            @Parameter(description = "DTO транзакции") @RequestBody @Valid TransactionDTO transactionDTO,
            @Parameter(description = "ID игрока") @PathVariable int id,
            BindingResult bindingResult) {
        Transaction transaction = convertToTransaction(transactionDTO);
        transaction.setPlayer(playerService.findById(id));
        transactionCreditValidator.validate(transaction, bindingResult);
        transactionService.save(transaction);
        transaction.getPlayer().setBalance(transaction.getPlayer().getBalance() + transaction.getValue());
        playerService.update(transaction.getPlayer(), id);
        playerHistoryService.save(new PlayerHistory(transaction.getPlayer(), PlayerHistoryEnum.CREDIT.name()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Transaction convertToTransaction(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, Transaction.class);
    }
    @ExceptionHandler
    private ResponseEntity<TransactionErrorResponse> handleException(TransactionException e) {
        TransactionErrorResponse response = new TransactionErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
