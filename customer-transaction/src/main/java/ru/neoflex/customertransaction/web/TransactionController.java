package ru.neoflex.customertransaction.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.customertransaction.backend.TransactionService;


/**
 * The type Transaction controller.
 */
@RestController
@RequestMapping(value = "transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    /**
     * Gets tanssction.
     *
     * @param aggregateId the aggregate id
     * @return the tansaction
     */
    @GetMapping
    @RequestMapping(value = "/list/{aggregateId}")
    public ResponseEntity getTansaction(@PathVariable String aggregateId) {
        return service.getTransactions(aggregateId);
    }
}
