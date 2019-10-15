package com.wallet.digital.db.dao;

import com.wallet.digital.db.TransactionDTO;

import java.util.List;

public interface TransactionDAO {

    public TransactionDTO createTransaction(TransactionDTO transactionDTO);

    public List<TransactionDTO> getTransactionsByAccId(String accoundId);

}
