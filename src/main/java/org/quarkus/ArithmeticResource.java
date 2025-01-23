package org.quarkus;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Resource class for performing basic arithmetic operations.
 *
 * Provides endpoints for addition, subtraction, multiplication, and division.
 * All operations are secured and require the "user" role.
 * Responses are returned in JSON format.
 */
@Path("/arithmetic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArithmeticResource {

    /**
     * Adds two numbers.
     *
     * @param request the request containing the two numbers to add.
     * @return the result of the addition.
     */
    @POST
    @Path("/add")
    @RolesAllowed("user")
    public OperationResponse add(OperationRequest request) {
        return new OperationResponse(request.firstNumber() + request.secondNumber());
    }

    /**
     * Subtracts the second number from the first number.
     *
     * @param request the request containing the two numbers to subtract.
     * @return the result of the subtraction.
     */
    @POST
    @Path("/subtract")
    @RolesAllowed("user")
    public OperationResponse sub(OperationRequest request) {
        return new OperationResponse(request.firstNumber() - request.secondNumber());
    }

    /**
     * Divides the first number by the second number.
     * Throws a BadRequestException if the second number is zero.
     *
     * @param request the request containing the two numbers to divide.
     * @return the result of the division rounded to the nearest 2 digits.
     * @throws BadRequestException if division by zero is attempted.
     */
    @POST
    @Path("/divide")
    @RolesAllowed("user")
    public DivOperationResponse div(OperationRequest request) {

        if (request.secondNumber()==0) {
            throw new BadRequestException("Division by zero is not allowed");
        }
        BigDecimal firstNumber = new BigDecimal(request.firstNumber());
        BigDecimal secondNumber = new BigDecimal(request.secondNumber());

        BigDecimal result = firstNumber.divide(secondNumber, 2 , RoundingMode.HALF_UP);
        return new DivOperationResponse(result);
    }

    /**
     * Multiplies two numbers.
     *
     * @param request the request containing the two numbers to multiply.
     * @return the result of the multiplication.
     */
    @POST
    @Path("/multiply")
    @RolesAllowed("user")
    public OperationResponse mul(OperationRequest request) {
        return new OperationResponse(request.firstNumber() * request.secondNumber());
    }
}
