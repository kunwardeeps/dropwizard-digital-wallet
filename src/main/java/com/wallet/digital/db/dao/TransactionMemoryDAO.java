package com.wallet.digital.db.dao;

import com.wallet.digital.db.TransactionDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TransactionMemoryDAO implements TransactionDAO {

    private Map<UUID, TransactionDTO> map = new ConcurrentHashMap<>();

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        map.put(transactionDTO.getTransactionID(), transactionDTO);
        return transactionDTO;
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccId(String accoundId) {
        List<TransactionDTO> transactionDTOList = map.entrySet()
                .stream()
                .filter(e -> (e.getValue().getFrom().equals(accoundId) || e.getValue().getTo().equals(accoundId)))
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        return transactionDTOList;
    }
}
