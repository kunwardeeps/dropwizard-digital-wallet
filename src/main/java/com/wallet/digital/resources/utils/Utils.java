package com.wallet.digital.resources.utils;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.db.AccountDTO;

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
}
