package ru.neoflex.management.eventHandler;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.neoflex.event.CreateEvent;
import ru.neoflex.event.UpdateStatusAccountEvent;
import ru.neoflex.management.backend.dao.DaoQuery;

/**
 * The type Event handler.
 */
@EventSubscriber
@Slf4j
@Component
public class EventHandlerManagement {
    private static final Logger logger = LoggerFactory.getLogger(EventHandlerManagement.class);



    @Autowired
    private DaoQuery service;

    /**
     * Register status.
     *
     * @param event the event
     */
    @EventHandlerMethod
    public void registerStatus(DispatchedEvent<CreateEvent> event) {
        logger.info("CreateAccountEvent received: {}", event);
        logger.info("Get CreateAccountEvent: [{}]. offset:[{}], swimlane:[{}]",
                event.getEvent(), event.getOffset(), event.getSwimlane());


        service.insertEvent(event.getEntityId(), event.getEvent().getStatus());


    }

    /**
     * Update phone.
     *
     * @param event the event
     */
    @EventHandlerMethod
    public void updatePhone(DispatchedEvent<UpdateStatusAccountEvent> event) {
        logger.info("UpdatePhoneAccountEvent received: {}", event);
        logger.info("Get UpdatePhoneAccountEvent: [{}]. offset:[{}], swimlane:[{}]",
                event.getEvent(), event.getOffset(), event.getSwimlane());

        service.changeStatus(event.getEvent().getStatus(), event.getEntityId());


    }


}
