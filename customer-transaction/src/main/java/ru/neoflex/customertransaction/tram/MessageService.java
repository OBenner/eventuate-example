package ru.neoflex.customertransaction.tram;




import lombok.Getter;

import java.util.Collections;

/**
 * The  Message service.
 */
@Getter
public class MessageService {
    /**
     * Instantiates a new Message service.
     *
     * @param myConsumer      the my consumer
     * @param messageConsumer the message consumer
     */
    public MessageService(TransactionMessageConsumer myConsumer,
                          io.eventuate.tram.messaging.consumer.MessageConsumer messageConsumer) {
        messageConsumer.subscribe("transactionSubscriber",
                Collections.singleton("TransactionDestination"),
                myConsumer::handler);
    }
}
