package com.github.gameserivcespring.transactionservice.automation;

import com.github.gameserivcespring.transactionservice.service.TransactionServiceImpl;

import java.util.Optional;

public abstract class AbstractService<M, D, I> {
    /**
     * Сохраняет модель в базу данных
     * @return Возвращает модель
     */
    public abstract M save(M model);

    /**
     * Находит модель по его ID
     * @param id ID модели
     * @return Возращает модель
     */
    public abstract Optional<M> getModel(I id);

    /**
     * Сохраняет обновленную модель
     * @param updateModel Обновленная модель
     * @return Возвращает обновленную модель
     */
    public abstract M updateModel(M updateModel);

    /**
     * Удаляет модель по ее ID
     * @param id ID модели
     */
    public abstract void deleteById(I id);

    /**
     * Конвертирует DTO модели в саму модель
     * @param dto DTO модели
     * @return Возращает модель
     */
    public abstract M convertToModel(D dto);

    /**
     * Конвертирует модель в DTO этой модели
     * @param model Модель
     * @return Возращает DTO модели
     */
    public abstract D convertToDTO(M model);
}
