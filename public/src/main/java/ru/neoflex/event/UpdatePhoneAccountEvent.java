package ru.neoflex.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The  Update phone account event.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhoneAccountEvent implements AccountEvent {
    private String phone;
}
