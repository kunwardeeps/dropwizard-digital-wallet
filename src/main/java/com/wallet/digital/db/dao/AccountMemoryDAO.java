package com.wallet.digital.db.dao;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.db.AccountDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountMemoryDAO implements AccountDAO {

    private Map<Integer, AccountDTO> accountMap = new HashMap<>();

    public AccountDTO createAccount(AccountDTO dto) {
        accountMap.put(dto.getId(), dto);
        return dto;
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountMap.values().stream().collect(Collectors.toList());
    }

}
