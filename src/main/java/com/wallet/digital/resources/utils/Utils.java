package com.wallet.digital.resources.utils;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.api.TransactionTO;
import com.wallet.digital.db.AccountDTO;
import com.wallet.digital.db.TransactionDTO;

import java.util.Date;
import java.util.UUID;

public class Utils {

    public static AccountDTO getAccountDTO(AccountTO accountTO) {
        AccountDTO dto = new AccountDTO();
        dto.setId(UUID.randomUUID());
        dto.setName(accountTO.getName());
        dto.setBalance(accountTO.getInitialAmount());
        dto.setDateCreated(new Date());
        return dto;
    }

    public static TransactionDTO getTransactioDTO(TransactionTO transactionTO) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionID(UUID.randomUUID());
        dto.setAmount(transactionTO.getAmount());
        dto.setFrom(transactionTO.getFrom());
        dto.setTo(transactionTO.getTo());
        dto.setDateCreated(new Date());
        return dto;
    }
}
