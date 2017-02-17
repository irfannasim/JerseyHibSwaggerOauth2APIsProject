package com.jaxrs;

import com.enums.ResponseEnum;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.model.UserRole;
import com.response.GenericApiResponse;
import com.service.UserRoleService;
import com.util.JsonUtil;
import com.util.LogUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;

@Path("/role")
public class RoleAPI {

    UserRoleService userRoleService;

    @GET
    @Path("/roles")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(httpMethod = "GET",
            value = "Get All Roles",
            notes = "This method will provide all roles existing in the system.",
            produces = "application/json",
            nickname = "getRoles",
            response = GenericApiResponse.class,
            tags = "getRoles",
            protocols = "http"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Roles provides successfully", response = GenericApiResponse.class),
            @ApiResponse(code = 500, message = "Oops, my fault. Something went wrong on the server side.")})
    public Response getRoles(@HeaderParam("authorization") String auth) {
        userRoleService = new UserRoleService();
        GenericApiResponse response = new GenericApiResponse();
        String result = "";
        LogUtil.log("User Role API :: finding all user roles", Level.INFO, null);

        try {
            response.setResponseData("");
            response.setResponseCode(ResponseEnum.USER_ROLE_NOT_FOUND
                    .getValue());
            response.setErrorMessage("Users Roles not found.");

            List<UserRole> roles = userRoleService.getAllUserRoles();

            if (roles != null) {
                String[] userRoleFieldNames = {"id", "name", "description"};
                FilterProvider filters = new SimpleFilterProvider().addFilter(
                        "UserRoleFilter", SimpleBeanPropertyFilter
                                .filterOutAllExcept(userRoleFieldNames));

                response.setResponseData(roles);
                response.setResponseCode(ResponseEnum.SUCCESS.getValue());
                response.setErrorMessage("");
                result = JsonUtil.pojoToJSONWithFilters(response, filters);
            }

        } catch (Exception ex) {
            LogUtil.log("find users roles failed", Level.SEVERE, ex);
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setErrorMessage("Internal Server Error.");
        }
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
}
