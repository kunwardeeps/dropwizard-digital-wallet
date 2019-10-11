package com.wallet.digital;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardDigitalWalletApplication extends Application<DropwizardDigitalWalletConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardDigitalWalletApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardDigitalWallet";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardDigitalWalletConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardDigitalWalletConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
