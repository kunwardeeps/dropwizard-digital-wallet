package com.wallet.digital.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.db.dao.AccountDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class has receive all REST requests for Accounts and process it.
 */
@Api(value = "accounts",
     tags = {"accounts"})
@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountResource.class);

    private AccountDAO accountDAO;

    /**
     * Constructor.
     */
    public AccountResource(final AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
     * Persist a {@link AccountTO} object.
     *
     * @param accountTO The object to persist.
     * @return An object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success.")
    })
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@ApiParam(value = "Account") @NotNull final AccountTO accountTO) {
        accountDAO.createAccount(accountTO);
        return Response.status(Response.Status.CREATED).build();
    }
}