package com.github.gameserivcespring.validator.transaction;

import com.github.gameserivcespring.repository.transaction.entity.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Валидатор для {@link Transaction}, который проверяет значение кредита на величину, не превышающую баланс
 */
@Component
public class TransactionDebitValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Transaction.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Transaction transaction = (Transaction) target;
        if(transaction.value() - transaction.player().balance() <= 0)
            errors.rejectValue("value", "Credit exceeds balance");
    }
}
