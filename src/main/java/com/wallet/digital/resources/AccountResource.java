package com.wallet.digital.resources;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wallet.digital.api.AccountTO;
import com.wallet.digital.api.ResponseTO;
import com.wallet.digital.db.AccountDTO;
import com.wallet.digital.db.dao.AccountDAO;

import com.wallet.digital.resources.utils.ResponseCodeEnum;
import com.wallet.digital.resources.utils.Utils;
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

    public AccountResource(final AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }


    /**
     * Get a {@link AccountDTO} by identifier.
     *
     * @param id the identifier.
     * @return A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success."),
            @ApiResponse(code = 404, message = "Account not found")
    })
    @GET
    @Path("/{id}")
    public Response getAccountDetails(@ApiParam(value = "id") @PathParam("id") @NotNull final String id) {
        LOGGER.info("getting account details for {}", id);
        AccountDTO dto = accountDAO.getAccountDetails(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseTO(ResponseCodeEnum.INVALID_ACCOUNT_ID)).build();
        }
        return Response.ok(dto).build();
    }

    /**
     * Get all {@link AccountDTO} objects.
     *
     * @return A object {@link Response} with the information of result this method.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success.")
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
            @ApiResponse(code = 201, message = "Account created!")
    })
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@ApiParam(value = "Account") @NotNull final AccountTO accountTO) {
        AccountDTO dto = accountDAO.createAccount(Utils.getAccountDTO(accountTO));
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
}