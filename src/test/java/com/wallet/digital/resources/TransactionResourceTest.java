package com.wallet.digital.resources;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.api.ResponseTO;
import com.wallet.digital.api.TransactionTO;
import com.wallet.digital.db.AccountDTO;
import com.wallet.digital.db.TransactionDTO;
import com.wallet.digital.db.dao.AccountDAO;
import com.wallet.digital.db.dao.AccountMemoryDAO;
import com.wallet.digital.db.dao.TransactionDAO;
import com.wallet.digital.db.dao.TransactionMemoryDAO;
import com.wallet.digital.resources.utils.ResponseCodeEnum;
import com.wallet.digital.resources.utils.Utils;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TransactionResourceTest {

    private static AccountDAO accountDAO = AccountMemoryDAO.getInstance();
    private static TransactionDAO transactionDAO = TransactionMemoryDAO.getInstance();

    private static AccountTO accountTO1;
    private static AccountDTO accountDTO1;

    private static AccountTO accountTO2;
    private static AccountDTO accountDTO2;

    private static AccountTO accountTO3;
    private static AccountDTO accountDTO3;

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TransactionResource(accountDAO, transactionDAO))
            .addResource(new AccountResource(accountDAO))
            .build();

    @Before
    public void setUp(){
        accountTO1 = new AccountTO();
        accountTO1.setInitialAmount(100);
        accountTO1.setName("Test1");
        accountDTO1 = Utils.getAccountDTO(accountTO1);

        accountTO2 = new AccountTO();
        accountTO2.setInitialAmount(100);
        accountTO2.setName("Test2");
        accountDTO2 = Utils.getAccountDTO(accountTO2);

        accountTO3 = new AccountTO();
        accountTO3.setInitialAmount(100);
        accountTO3.setName("Test2");
        accountDTO3 = Utils.getAccountDTO(accountTO3);
    }

    @Test
    public void testListTransactions() {
        Response response1 = resources.target("/accounts/create").request().post(Entity.entity(accountTO1, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(response1);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response1.getStatus());
        AccountDTO dto1 = response1.readEntity(AccountDTO.class);

        Response response2 = resources.target("/accounts/create").request().post(Entity.entity(accountTO2, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(response2);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response2.getStatus());
        AccountDTO dto2 = response2.readEntity(AccountDTO.class);

        Response response3 = resources.target("/accounts/create").request().post(Entity.entity(accountTO3, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(response3);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response3.getStatus());
        AccountDTO dto3 = response3.readEntity(AccountDTO.class);

        TransactionTO transactionTO = new TransactionTO();
        transactionTO.setAmount(50.0);
        transactionTO.setFrom(dto1.getId());
        transactionTO.setTo(dto2.getId());
        Response txnResp = resources.target("/transactions/transfer").request().post(Entity.entity(transactionTO, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(txnResp);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), txnResp.getStatus());

        Response resp1 = resources.target("/transactions/account/" + dto1.getId()).request().get();
        Assert.assertNotNull(resp1);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), resp1.getStatus());
        List<TransactionDTO> txnList = resp1.readEntity(new GenericType<List<TransactionDTO>>(){});
        Assert.assertEquals(txnList.size(), 1);

        Response resp2 = resources.target("/transactions/account/" + dto3.getId()).request().get();
        Assert.assertNotNull(resp2);
        Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resp2.getStatus());
    }

    @Test
    public void testCreateTransaction() {
        Response response1 = resources.target("/accounts/create").request().post(Entity.entity(accountTO1, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(response1);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response1.getStatus());
        AccountDTO dto1 = response1.readEntity(AccountDTO.class);

        Response response2 = resources.target("/accounts/create").request().post(Entity.entity(accountTO2, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(response2);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response2.getStatus());
        AccountDTO dto2 = response2.readEntity(AccountDTO.class);

        TransactionTO transactionTO = new TransactionTO();
        transactionTO.setAmount(50.0);
        transactionTO.setFrom(dto1.getId());
        transactionTO.setTo(dto2.getId());
        Response response3 = resources.target("/transactions/transfer").request().post(Entity.entity(transactionTO, MediaType.APPLICATION_JSON));
        Assert.assertNotNull(response3);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(), response3.getStatus());

        Response resp1 = resources.target("/accounts/" + dto1.getId()).request().get();
        Assert.assertNotNull(resp1);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), resp1.getStatus());
        AccountDTO fromDto = resp1.readEntity(AccountDTO.class);

        Response resp2 = resources.target("/accounts/" + dto2.getId()).request().get();
        Assert.assertNotNull(resp2);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), resp2.getStatus());
        AccountDTO toDto = resp2.readEntity(AccountDTO.class);

        Assert.assertEquals(fromDto.getBalance(), 50.0, 0.0);
        Assert.assertEquals(toDto.getBalance(), 150.0, 0.0);
    }
}