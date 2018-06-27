package ru.neoflex.customertransaction.tram;


import io.eventuate.EventHandlerMethod;
import io.eventuate.tram.messaging.common.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.neoflex.customertransaction.dao.DaoQuery;


/**
 * The type Message consumer.
 */
public class TransactionMessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(TransactionMessageConsumer.class);


    @Autowired
    private DaoQuery query;

    /**
     * Handler.
     *
     * @param message the message
     */
    @EventHandlerMethod
    public void handler(Message message) {
        logger.info("message received: {}", message.getHeaders());

        String aggregateId = message.getHeader("aggregateId").get();
        logger.info("aggregateId received: {}", aggregateId);
        String operation = message.getHeader("operation").get();
        logger.info("operation received: {}", operation);
        String amount = message.getHeader("amount").get();
        logger.info("amount received: {}", amount);
        query.newTransaction(aggregateId, operation, amount);
    }

}
