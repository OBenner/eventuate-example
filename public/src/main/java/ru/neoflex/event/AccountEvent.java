package ru.neoflex.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

/**
 * The interface Account event.
 */
@EventEntity(entity = "ru.neoflex.customer.AccountAggregate")
public interface AccountEvent extends Event {
}
