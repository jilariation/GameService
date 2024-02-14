package com.github.gameserivcespring.validator.transaction;

import com.github.gameserivcespring.repository.entity.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TransactionDebitValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Transaction.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Transaction transaction = (Transaction) target;
        if(transaction.getValue() - transaction.getPlayer().getBalance() <= 0)
            errors.rejectValue("value", "Credit exceeds balance");
    }
}
