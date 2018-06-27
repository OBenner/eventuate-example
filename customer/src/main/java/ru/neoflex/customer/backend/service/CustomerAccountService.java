package ru.neoflex.customer.backend.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityNotFoundException;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EntityWithMetadata;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.customer.AccountAggregate;
import ru.neoflex.customer.backend.exceptions.AccountAggregateNotFoundException;
import ru.neoflex.customer.backend.model.CreateCustomerEntity;
import ru.neoflex.customer.command.*;
import ru.neoflex.model.Account;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * The  Customer account service.
 */
@Slf4j
@Service
public class CustomerAccountService {

    private final AggregateRepository<AccountAggregate, AccountCommand> aggregateRepository;
    /**
     * The Account.
     */
    @Getter
    Account account = new Account();


    /**
     * Instantiates a new Customer account service.
     *
     * @param aggregateRepository the aggregate repository
     */
    public CustomerAccountService(AggregateRepository<AccountAggregate, AccountCommand> aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }


    /**
     * Create account completable future.
     *
     * @param customerEntity the customer entity
     * @return the completable future
     */
    public CompletableFuture<EntityWithIdAndVersion<AccountAggregate>> createAccount(
            CreateCustomerEntity customerEntity) {
        return aggregateRepository.save(new CreateAccountCommand(customerEntity));
    }


    /**
     * Change amount completable future.
     *
     * @param amount      the amount
     * @param aggregateId the aggregate id
     * @param operation   the operation
     * @return the completable future
     */
    public CompletableFuture<EntityWithIdAndVersion<AccountAggregate>> changeAmount(double amount, String aggregateId, String operation) {
        return aggregateRepository.update(aggregateId, new UpdateAmountAccountCommand(amount, operation));
    }


    /**
     * Change status completable future.
     *
     * @param aggregateId the aggregate id
     * @param status      the status
     * @return the completable future
     */
    public CompletableFuture<EntityWithIdAndVersion<AccountAggregate>> changeStatus( String aggregateId, String status) {
        return aggregateRepository.update(aggregateId, new UpdateStatusAccountCommand(status));
    }


    /**
     * Change phone completable future.
     *
     * @param phone       the phone
     * @param aggregateId the aggregate id
     * @return the completable future
     */
    public CompletableFuture<EntityWithIdAndVersion<AccountAggregate>> changePhone(String phone, String aggregateId) {
        return aggregateRepository.update(aggregateId, new UpdatePhoneAccountCommand(phone));
    }


    /**
     * Get aggregate entity with metadata.
     *
     * @param aggregateId the aggregate id
     * @return the entity with metadata
     * @throws AccountAggregateNotFoundException the account aggregate not found exception
     */
    public EntityWithMetadata<AccountAggregate> get(String aggregateId) throws AccountAggregateNotFoundException {
        try {
            return aggregateRepository.find(aggregateId).get();
        } catch (ExecutionException e) {
            if (e.getCause().getClass().equals(EntityNotFoundException.class)) {
                log.warn(String.format("Account aggregate with ID [%s] not found. Exception class [%s]", aggregateId, e.getClass()));
                throw new AccountAggregateNotFoundException(aggregateId);
            } else {
                log.error("Error while get Account aggregate from repository", e);
                throw new RuntimeException(e);
            }
        } catch (InterruptedException e) {
            log.error("Error while get Account aggregate from repository", e);
            throw new RuntimeException(e);
        }
    }

}
