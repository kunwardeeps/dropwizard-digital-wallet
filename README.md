# Dropwizard Digital Wallet

How to start the DropwizardDigitalWallet application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-digital-wallet-1.0.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080/dropwizard-digital-wallet/swagger`

Unit Testing
---
1. On running `mvn clean install` application will be tested automatically.
1. To run the tests manually, run the `TransactionResourceTest.java` and `AccountResourceTest.java` classes.

How to transfer money between accounts
---
Please refer the Swagger documentation for the API usage. Then follow these steps:
1. On creating accounts using `/accounts/create` note the account id being generated. 
This will be used in the transaction API
1. Use the `/transactions/transfer` endpoint to transfer money from one account to another. 
On successful operation, the transaction details will be returned.

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
