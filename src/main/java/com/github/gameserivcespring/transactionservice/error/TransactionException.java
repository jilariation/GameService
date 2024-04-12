package com.github.gameserivcespring.transactionservice.error;

public class TransactionException extends RuntimeException {
    public TransactionException(String msg){
        super(msg);
    }
}
