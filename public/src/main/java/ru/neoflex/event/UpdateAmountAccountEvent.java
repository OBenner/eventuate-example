package ru.neoflex.event;

import lombok.*;

/**
 * The  Update amount account event.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateAmountAccountEvent implements AccountEvent {
    private double amount;
    private String operation;
}
