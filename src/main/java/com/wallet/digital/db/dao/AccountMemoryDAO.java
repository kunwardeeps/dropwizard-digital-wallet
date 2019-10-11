package com.wallet.digital.db.dao;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.db.AccountDTO;

import java.util.HashMap;
import java.util.Map;

public class AccountMemoryDAO implements AccountDAO {

    private Map<Integer, AccountDTO> accountMap = new HashMap<>();

    public AccountDTO createAccount(AccountTO accountTO) {
        AccountDTO dto = new AccountDTO();
        //TODO
        accountMap.put(dto.getId(), dto);
        return dto;
    }

}
