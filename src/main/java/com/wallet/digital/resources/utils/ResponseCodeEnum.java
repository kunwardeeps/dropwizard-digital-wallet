package com.wallet.digital.resources.utils;

public enum ResponseCodeEnum {
    SUCCESS(0, "Success"),
    GENERIC_ERROR(99, "Internal Service Error"),
    DATABASE_ERROR(10, "Error while performing an operation on database."),
    INVALID_ACCOUNT_ID(50, "Invalid Account ID"),
    INVALID_RECEIVER_ACCOUNT_ID(55, "Invalid Receiver Account ID"),
    INSUFFICIENT_FUNDS(60, "Not enough funds available");

    private int responseCode;
    private String description;

    private ResponseCodeEnum(int responseCode, String description) {
        this.responseCode = responseCode;
        this.description = description;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getDescription() {
        return description;
    }

}
