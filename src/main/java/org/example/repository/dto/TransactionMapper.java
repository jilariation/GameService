package org.example.repository.dto;

import org.example.repository.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "value", target = "value")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "value", target = "value")
    Transaction TransactionDTOTotransaction(TransactionDTO transactionDTO);
}
