package com.wallet.digital.resources;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.api.ResponseTO;
import com.wallet.digital.api.TransactionTO;
import com.wallet.digital.db.AccountDTO;
import com.wallet.digital.db.TransactionDTO;
import com.wallet.digital.db.dao.AccountDAO;
import com.wallet.digital.db.dao.TransactionDAO;
import com.wallet.digital.resources.utils.ResponseCodeEnum;
import com.wallet.digital.resources.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class has receive all REST requests for Transactions and process it.
 */
@Api(value = "transactions",
     tags = {"transactions"})
@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionResource.class);

    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    /**
     * Constructor.
     */
    public TransactionResource(final AccountDAO accountDAO, final TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }

    /**
     * Create Transaction.
     *
     * @param transactionTO The object to persist.
     * @return An object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account created!")
    })
    @POST
    @Path("/transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@ApiParam(value = "TransactionTO") @NotNull final TransactionTO transactionTO) {
        TransactionDTO transactionDTO = null;
        synchronized (this) {
            AccountDTO fromAccount = accountDAO.getAccountDetails(transactionTO.getFrom().toString());
            if (fromAccount.getBalance() < transactionTO.getAmount()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseTO(ResponseCodeEnum.INSUFFICIENT_FUNDS)).build();
            }
            accountDAO.withdraw(transactionTO.getFrom().toString(), transactionTO.getAmount());
            accountDAO.deposit(transactionTO.getTo().toString(), transactionTO.getAmount());
            transactionDTO = transactionDAO.createTransaction(Utils.getTransactioDTO(transactionTO));
        }
        return Response.status(Response.Status.CREATED).entity(transactionDTO).build();
    }

    /**
     * Get all {@link TransactionDTO} objects involved with given account.
     *
     * @return A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success.")
    })
    @GET
    @Path("/account/{id}")
    public Response getTransactionsByAccId(@ApiParam(value = "id") @PathParam("id") @NotNull final String id) {
        final List<TransactionDTO> transactions = transactionDAO.getTransactionsByAccId(id);
        if (transactions.isEmpty()) {
            return Response.accepted("No transactions found!")
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response.ok(transactions).build();
    }


}