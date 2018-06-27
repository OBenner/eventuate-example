package ru.neoflex.customertransaction.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.neoflex.customertransaction.dao.DaoQuery;
import ru.neoflex.customertransaction.web.NotFound;
import ru.neoflex.model.Transaction;


import java.util.List;

/**
 * The type Transaction service.
 */
@Service
public class TransactionService {


    @Autowired
    private DaoQuery query;

    /**
     * Gets transactions.
     *
     * @param aggregateId the aggregate id
     * @return the transactions
     */
    public ResponseEntity getTransactions(String aggregateId) {
        List<Transaction> transactions = query.transactionList(aggregateId);
        if (transactions == null) {
            throw new NotFound("Account not found");
        }
        return new ResponseEntity(transactions, HttpStatus.OK);
    }


}
