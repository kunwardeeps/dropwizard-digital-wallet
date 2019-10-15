package com.wallet.digital;

import com.wallet.digital.db.dao.AccountDAO;
import com.wallet.digital.db.dao.AccountMemoryDAO;
import com.wallet.digital.db.dao.TransactionMemoryDAO;
import com.wallet.digital.health.DigitalWalletHealthCheck;
import com.wallet.digital.resources.AccountResource;
import com.wallet.digital.resources.TransactionResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropwizardDigitalWalletApplication extends Application<DropwizardDigitalWalletConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DropwizardDigitalWalletApplication.class);

    public static void main(final String[] args) throws Exception {
        LOGGER.info("Starting Digital Wallet application...");
        new DropwizardDigitalWalletApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardDigitalWallet";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardDigitalWalletConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardDigitalWalletConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final DropwizardDigitalWalletConfiguration dropwizardDigitalWalletConfiguration) {
                return dropwizardDigitalWalletConfiguration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(final DropwizardDigitalWalletConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new AccountResource(AccountMemoryDAO.getInstance()));
        environment.jersey().register(new TransactionResource(AccountMemoryDAO.getInstance(), TransactionMemoryDAO.getInstance()));
        environment.healthChecks().register("DigitalWalletHealthCheck",
                new DigitalWalletHealthCheck());
    }

}
