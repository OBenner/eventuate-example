package ru.neoflex.model;

import lombok.*;

/**
 * The  Account aggregate.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    private String name;
    private double amount;
    private String status;
    private String phone;
    private String lastOperation;
}
