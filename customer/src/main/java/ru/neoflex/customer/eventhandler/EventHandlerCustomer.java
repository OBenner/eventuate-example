package ru.neoflex.customer.eventhandler;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import io.eventuate.tram.messaging.producer.MessageBuilder;
import io.eventuate.tram.messaging.producer.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import ru.neoflex.customer.backend.dao.DaoQuery;
import ru.neoflex.event.CreateEvent;
import ru.neoflex.event.UpdateAmountAccountEvent;
import ru.neoflex.event.UpdatePhoneAccountEvent;
import ru.neoflex.tramMessage.ManageMessage;

/**
 * The  Event handler customer.
 */
@EventSubscriber
@Slf4j
public class EventHandlerCustomer {
    private static final Logger logger = LoggerFactory.getLogger(EventHandlerCustomer.class);

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private DaoQuery query;

    /**
     * Change amount events handler.
     *
     * @param event the event
     */
    @EventHandlerMethod
  //  @EventListener
    public void changeAmountEvents(DispatchedEvent<UpdateAmountAccountEvent> event) {
        logger.info("UpdateAmountAccountEvent received: {}", event);
        logger.info("Get UpdateAmountAccountEvent: [{}]. offset:[{}], swimlane:[{}]",
                event.getEvent(), event.getOffset(), event.getSwimlane());

        messageProducer.send("TransactionDestination",
                MessageBuilder.
                        withPayload(new ManageMessage().toString()).
                        withHeader("aggregateId", event.getEntityId()).
                        withHeader("amount", String.valueOf(event.getEvent().getAmount())).
                        withHeader("operation", event.getEvent().getOperation()).
                        build());
        logger.info("after send message");

    }

    /**
     * Register status.
     *
     * @param event the event
     */
    @EventHandlerMethod
  //  @EventListener
    public void registerStatus(DispatchedEvent<CreateEvent> event) {
        logger.info("CreateAccountEvent received: {}", event);
        logger.info("Get CreateAccountEvent: [{}]. offset:[{}], swimlane:[{}]",
                event.getEvent(), event.getOffset(), event.getSwimlane());


        query.insertEvent(event.getEntityId(), event.getEvent().getPhone());
        logger.info("Save in DB");

    }


    /**
     * Update phone.
     *
     * @param event the event
     */
    @EventHandlerMethod
    public void updatePhone(DispatchedEvent<UpdatePhoneAccountEvent> event) {
        logger.info("CreateAccountEvent received: {}", event);
        logger.info("Get CreateAccountEvent: [{}]. offset:[{}], swimlane:[{}]",
                event.getEvent(), event.getOffset(), event.getSwimlane());


        query.changePhone( event.getEvent().getPhone(),event.getEntityId());


    }

}
