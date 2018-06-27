package ru.neoflex.model;

import lombok.*;

/**
 * The  Management entity from db.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerEntity {
    private String aggregateId;
    private String phone;
}
