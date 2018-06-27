package ru.neoflex.customer.web;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.customer.backend.exceptions.AccountAggregateNotFoundException;
import ru.neoflex.customer.backend.exceptions.ErrorResponse;
import ru.neoflex.customer.backend.model.CreateCustomerEntity;
import ru.neoflex.customer.backend.service.CustomerService;
import ru.neoflex.model.Account;

import java.util.concurrent.ExecutionException;

/**
 * The Customer controller.
 */
@Api(value = "customer-service", description = "Main service")
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    /**
     * Create account.
     *
     * @param body the body
     * @return the response entity
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @PostMapping(value = "/create")
    @ApiOperation(value = "Create account")
    public ResponseEntity createAccount(@RequestBody CreateCustomerEntity body) throws ExecutionException, InterruptedException {
        return customerService.create(body);
    }


    /**
     * Gets account info.
     *
     * @param phone the phone
     * @return the account info
     * @throws AccountAggregateNotFoundException the account aggregate not found exception
     */
    @GetMapping(value = "/info")
    @ApiOperation(value = "Account info")
    public ResponseEntity getAccountInfo(@RequestHeader String phone) throws AccountAggregateNotFoundException {
        return customerService.getAccountInfo(phone);
    }

    /**
     * Change amount.
     *
     * @param operation the operation
     * @param amount    the amount
     * @param phone     the phone
     * @return the response entity
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @PutMapping(value = "/cash/{operation}")
    @ApiOperation(value = "Operation on account")
    public ResponseEntity changeAmount(
            @PathVariable String operation,
            @RequestHeader double amount,
            @RequestHeader String phone) throws ExecutionException, InterruptedException {
        return customerService.updateAmount(operation, amount, phone);
    }

    /**
     * Change phone.
     *
     * @param phone       the phone
     * @param aggregateId the aggregate id
     * @return the response entity
     */
    @PutMapping(value = "/changePhone")
    @ApiOperation(value = "Operation on account")
    public ResponseEntity changePhone(@RequestHeader String phone, @RequestHeader String aggregateId) {
        return customerService.updatePhone(phone, aggregateId);
    }

    /**
     * Confirm account.
     *
     * @param phone the phone
     * @return the response entity
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @GetMapping(value = "/confirm")
    @ApiOperation(value = "Account confirm")
    public ResponseEntity confirmAccount(@RequestHeader String phone) throws ExecutionException, InterruptedException {
        return customerService.confirm(phone);
    }

    /**
     * Gets aggregate id.
     *
     * @param phone the phone
     * @return the aggregate id
     */
    @GetMapping(value = "/aggregateId")
    @ApiOperation(value = "get aggregate Id")
    public ResponseEntity getAggregateId(@RequestHeader String phone) {
        return customerService.getAggregateId(phone);
    }

    /**
     * Gets transaction list.
     *
     * @param phone the phone
     * @return the transaction list
     */
    @GetMapping(value = "/transaction/list")
    @ApiOperation(value = "get transaction list")
    public ResponseEntity getTransactionList(@RequestHeader String phone) {
        return customerService.getTransactionList(phone);
    }



}
