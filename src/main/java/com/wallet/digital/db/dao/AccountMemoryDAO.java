package com.wallet.digital.db.dao;

import com.wallet.digital.db.AccountDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AccountMemoryDAO implements AccountDAO {

    private static AccountMemoryDAO dao;

    private AccountMemoryDAO() {}

    private Map<UUID, AccountDTO> accountMap = new ConcurrentHashMap<>();

    public AccountDTO createAccount(AccountDTO dto) {
        accountMap.put(dto.getId(), dto);
        return dto;
    }

    @Override
    public AccountDTO getAccountDetails(String id) {
        return accountMap.get(UUID.fromString(id));
    }

    @Override
    public AccountDTO deposit(String accountId, double amount) {
        UUID accountUUID = UUID.fromString(accountId);
        AccountDTO dto = accountMap.get(accountUUID);
        if (dto != null) {
            dto.setBalance(dto.getBalance() + amount);
            accountMap.put(accountUUID, dto);
        }
        return dto;
    }

    @Override
    public AccountDTO withdraw(String accountId, double amount) {
        UUID accountUUID = UUID.fromString(accountId);
        AccountDTO dto = accountMap.get(accountUUID);
        if (dto != null) {
            dto.setBalance(dto.getBalance() - amount);
            accountMap.put(accountUUID, dto);
        }
        return dto;
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountMap.values().stream().collect(Collectors.toList());
    }

    public static AccountMemoryDAO getInstance() {
        if (dao == null) {
            dao = new AccountMemoryDAO();
        }
        return dao;
    }

}
