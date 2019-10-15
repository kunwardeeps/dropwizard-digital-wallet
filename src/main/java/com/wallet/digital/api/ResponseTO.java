package com.wallet.digital.api;

import com.wallet.digital.resources.utils.ResponseCodeEnum;

public class ResponseTO {

    private int responseCode;
    private String message;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseTO() {
    }

    public ResponseTO(ResponseCodeEnum responseCodeEnum) {
        this.responseCode = responseCodeEnum.getResponseCode();
        this.message = responseCodeEnum.getDescription();
    }
}