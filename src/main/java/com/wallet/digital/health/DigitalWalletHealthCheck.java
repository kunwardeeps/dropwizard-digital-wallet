package com.wallet.digital.health;

import com.codahale.metrics.health.HealthCheck;

public class DigitalWalletHealthCheck extends HealthCheck {

    @Override
    protected Result check() {
        //TODO
        return Result.healthy();
    }
}