package ru.neoflex.customer.backend.model;

import lombok.*;

/**
 * The  Create customer entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCustomerEntity {
    private String name;
    private String phone;
}
