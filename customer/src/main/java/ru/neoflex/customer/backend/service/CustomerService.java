package ru.neoflex.customer.backend.service;

import io.eventuate.EntityWithIdAndVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.neoflex.customer.AccountAggregate;
import ru.neoflex.customer.backend.dao.DaoQuery;
import ru.neoflex.customer.backend.exceptions.AccountAggregateNotFoundException;
import ru.neoflex.customer.backend.exceptions.ErrorResponse;
import ru.neoflex.customer.backend.model.CreateCustomerEntity;
import ru.neoflex.customer.backend.exceptions.NotFound;
import ru.neoflex.model.Account;
import ru.neoflex.model.CustomerEntity;
import ru.neoflex.model.ManagementEntity;
import ru.neoflex.model.Transaction;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The  Customer service.
 */
@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private DaoQuery query;

    @Autowired
    private CustomerAccountService accountService;


    @Autowired
    private TransportService transportService;


    /**
     * Create account.
     *
     * @param entity the entity
     * @return the response entity
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public ResponseEntity create(CreateCustomerEntity entity) throws ExecutionException, InterruptedException {
        logger.info("Customer data {}", entity);
        CompletableFuture<EntityWithIdAndVersion<AccountAggregate>> aggregate = accountService.createAccount(entity);
        logger.info("aggregate {}", aggregate.get().getAggregate().getAccount());
        return new ResponseEntity(aggregate.get().getAggregate().getAccount(), HttpStatus.OK);

    }


    /**
     * Update phone.
     *
     * @param phone       the phone
     * @param aggregateId the aggregate id
     * @return the response entity
     */
    public ResponseEntity updatePhone(String phone, String aggregateId) {
        CompletableFuture<EntityWithIdAndVersion<AccountAggregate>> aggregate = accountService.changePhone(phone, aggregateId);
        Account account = new Account();
        try {
            account = aggregate.get().getAggregate().getAccount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(account, HttpStatus.OK);

    }


    /**
     * Update amount.
     *
     * @param operation the operation
     * @param amount    the amount
     * @param phone     the phone
     * @return the response entity
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public ResponseEntity updateAmount(String operation, Double amount, String phone)
            throws ExecutionException, InterruptedException {
        CustomerEntity entity = query.findByPhone(phone);
        if (entity == null) {
            throw new NotFound("Account not found");
        }
        Account account = new Account();
        try {
            account = accountService.get(entity.getAggregateId()).getEntity().getAccount();
        } catch (AccountAggregateNotFoundException e) {
            throw new NotFound("Account not found");
        }
        if (account.getStatus().equals("created")) {
            throw new ErrorResponse("Please confirm you account");
        }
        double currentAmount = account.getAmount();
        if (operation.equals("debit")) {
            currentAmount += amount;
        } else if (operation.equals("credit")) {
            currentAmount -= amount;
        }
        logger.info("currentAmount {}", currentAmount);
        CompletableFuture<EntityWithIdAndVersion<AccountAggregate>> aggregate =
                accountService.changeAmount(currentAmount, entity.getAggregateId(), operation);
        logger.info("currentAmount {}", currentAmount);

        try {
            account = aggregate.get().getAggregate().getAccount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(account, HttpStatus.OK);

    }


    /**
     * Gets account info.
     *
     * @param phone the phone
     * @return the account info
     */
    public ResponseEntity getAccountInfo(String phone) {
        CustomerEntity entity = query.findByPhone(phone);
        if (entity == null) {
            throw new NotFound("Account not found");
        }
        Account account = new Account();
        try {
            account = accountService.get(entity.getAggregateId()).getEntity().getAccount();
        } catch (AccountAggregateNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(account, HttpStatus.OK);
    }

    /**
     * Gets aggregate id.
     *
     * @param phone the phone
     * @return the aggregate id
     */
    public ResponseEntity getAggregateId(String phone) {
        CustomerEntity entity = query.findByPhone(phone);
        logger.info("CustomerEntity {}", entity);
        String aggregateId = entity.getAggregateId();
        return new ResponseEntity(aggregateId, HttpStatus.OK);
    }

    /**
     * Gets transaction list.
     *
     * @param phone the phone
     * @return the transaction list
     */
    public ResponseEntity getTransactionList(String phone) {
        CustomerEntity entity = query.findByPhone(phone);
        logger.info("CustomerEntity {}", entity);
        String aggregateId = entity.getAggregateId();
        List<Transaction> transactions = transportService.getAccountTransaction(aggregateId);
        return new ResponseEntity(transactions, HttpStatus.OK);
    }


    /**
     * Confirm response entity.
     *
     * @param phone the phone
     * @return the response entity
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public ResponseEntity confirm(String phone) throws ExecutionException, InterruptedException {
        CustomerEntity customerEntity = query.findByPhone(phone);
        logger.info("CustomerEntity {}", customerEntity);
        ManagementEntity managementEntity = transportService.confirmStatus(customerEntity.getAggregateId());
        CompletableFuture<EntityWithIdAndVersion<AccountAggregate>> aggregate =
                accountService.changeStatus(customerEntity.getAggregateId(), managementEntity.getAccount_status());
        return new ResponseEntity(aggregate.get().getAggregate().getAccount(), HttpStatus.OK);
    }
}
