package com.wallet.digital.db.dao;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.db.AccountDTO;

import java.util.List;

public interface AccountDAO {

    public AccountDTO createAccount(AccountDTO dto);

    AccountDTO getAccountDetails(String id);

    List<AccountDTO> getAll();
}
