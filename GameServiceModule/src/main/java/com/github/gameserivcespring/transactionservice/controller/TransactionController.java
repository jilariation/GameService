package com.github.gameserivcespring.transactionservice.controller;

import com.github.gameserivcespring.transactionservice.repository.dto.TransactionDTO;
import com.github.gameserivcespring.transactionservice.service.TransactionServiceImpl;
import com.github.gameserivcespring.transactionservice.util.TransactionCreditValidator;
import com.github.gameserivcespring.transactionservice.util.TransactionDebitValidator;
import com.github.gameserivcespring.userservice.service.PlayerServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    private final PlayerServiceImpl playerService;
    private final TransactionServiceImpl transactionService;
    private final TransactionDebitValidator transactionDebitValidator;
    private final TransactionCreditValidator transactionCreditValidator;

    @PostMapping("/transaction/debit")
    public ResponseEntity<HttpStatus> debit(
            @Parameter(description = "DTO транзакции") @RequestBody @Valid TransactionDTO transactionDTO,
            BindingResult bindingResult) {
        var transaction = transactionService.convertToModel(transactionDTO);
        var player = playerService.getModel();
        transaction.setUser(player);
        transactionDebitValidator.validate(transaction, bindingResult);
        transactionService.save(transaction);
//        playerService.updateModel((-1)*transaction.getValue(), player);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> credit(
            @Parameter(description = "DTO транзакции") @RequestBody @Valid TransactionDTO transactionDTO,
            BindingResult bindingResult) {
        var transaction = transactionService.convertToModel(transactionDTO);
        var player = playerService.getModel();
        transaction.setUser(player);
        transactionCreditValidator.validate(transaction, bindingResult);
        transactionService.save(transaction);
//        playerService.update(transaction.getValue(), player);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}