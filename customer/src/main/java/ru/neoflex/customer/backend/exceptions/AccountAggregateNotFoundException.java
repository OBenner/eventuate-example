package ru.neoflex.customer.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The type Account aggregate not found exception.
 */
@Getter @AllArgsConstructor
public class AccountAggregateNotFoundException extends Exception {
    private String aggregateId;
}
