package com.wallet.digital.api;

import java.util.Date;
import java.util.UUID;

public class TransactionTO {

    private UUID from;
    private UUID to;
    private long amount;

    public UUID getFrom() {
        return from;
    }

    public void setFrom(UUID from) {
        this.from = from;
    }

    public UUID getTo() {
        return to;
    }

    public void setTo(UUID to) {
        this.to = to;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
