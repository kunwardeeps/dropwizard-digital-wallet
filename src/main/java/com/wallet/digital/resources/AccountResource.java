package com.wallet.digital.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.db.AccountDTO;
import com.wallet.digital.db.dao.AccountDAO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
     * Get all {@link AccountDTO} objects.
     *
     * @return A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success."),
            @ApiResponse(code = 404, message = "Accounts not found")
    })
    @GET
    @Path("/all")
    public Response all() {
        final List<AccountDTO> donutsFind = accountDAO.getAll();
        if (donutsFind.isEmpty()) {
            return Response.accepted("No accounts found!")
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response.ok(donutsFind).build();
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
        AccountDTO dto = new AccountDTO();
        dto.setName(accountTO.getName());
        //TODO
        accountDAO.createAccount(dto);
        return Response.status(Response.Status.CREATED).build();
    }
}