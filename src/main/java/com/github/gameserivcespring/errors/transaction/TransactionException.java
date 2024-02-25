package com.github.gameserivcespring.errors.transaction;

public class TransactionException extends RuntimeException {
    public TransactionException(String msg){
        super(msg);
    }
}
