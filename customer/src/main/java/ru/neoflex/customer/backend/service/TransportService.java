package ru.neoflex.customer.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import ru.neoflex.customer.backend.exceptions.NotFound;
import ru.neoflex.model.ManagementEntity;
import ru.neoflex.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * The Transport service.
 */
@Service
public class TransportService {
    private static final Logger logger = LoggerFactory.getLogger(TransportService.class);


    @Autowired
    @Qualifier("management")
    private WebClient management;

    @Autowired
    @Qualifier("transaction")
    private WebClient transaction;


    /**
     * Confirm status management entity.
     *
     * @param aggregateId the aggregate id
     * @return the management entity
     */
    public ManagementEntity confirmStatus(String aggregateId) {
        logger.info("aggregateId to confirm {}", aggregateId);
        ManagementEntity entity = new ManagementEntity();
        ClientResponse response = management.get().uri(
                uriBuilder -> uriBuilder
                        .path("management/confirm/" + aggregateId).build())
                .exchange()
                .block();
        logger.info("Status : {} .", response.statusCode().toString());
        if (response.statusCode().value() == 404) {
            logger.info("response 404: {} .", response.statusCode().getReasonPhrase());
            throw new NotFound(response.statusCode().getReasonPhrase());
        }
        entity = response.bodyToMono(ManagementEntity.class).block();
        logger.info("Entity from confirm {}", entity);
        return entity;
    }


    /**
     * Gets account transaction.
     *
     * @param aggregateId the aggregate id
     * @return the account transaction
     */
    public List<Transaction> getAccountTransaction(String aggregateId) {
        List<Transaction> transactions = new ArrayList();
        ClientResponse response = transaction.get().uri(
                uriBuilder -> uriBuilder
                        .path("/transaction/list/" + aggregateId).build())
                .exchange().block();
        if (response.statusCode().value() == 404) {
            logger.info("response 404: {} .", response.statusCode().getReasonPhrase());
            throw new NotFound(response.statusCode().getReasonPhrase());
        }
        transactions = response.bodyToFlux(Transaction.class).collectList().block();
        return transactions;
    }
}
