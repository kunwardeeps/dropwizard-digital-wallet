package com.wallet.digital.db.dao;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.db.AccountDTO;

public interface AccountDAO {

    public AccountDTO createAccount(AccountTO accountTO);

}
