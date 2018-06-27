package ru.neoflex.model;

import lombok.*;

/**
 * The  Transaction entity from db.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    private String aggregateId;
    private String operation;
    private String amount;
}
