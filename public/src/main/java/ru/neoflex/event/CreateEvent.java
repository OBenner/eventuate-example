package ru.neoflex.event;

import lombok.*;


/**
 * The  Create account event.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateEvent implements AccountEvent {
    private String name;
    private double amount;
    private String status;
    private String phone;
}
