package com.wallet.digital.db.dao;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.db.AccountDTO;

import java.util.List;

public interface AccountDAO {

    public AccountDTO createAccount(AccountDTO dto);

    AccountDTO getAccountDetails(String id);

    public AccountDTO deposit(String accountId, double amount);

    public AccountDTO withdraw(String accountId, double amount);

    List<AccountDTO> getAll();
}
