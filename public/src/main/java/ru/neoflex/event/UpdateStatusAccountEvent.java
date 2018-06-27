package ru.neoflex.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The  Update status account event.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusAccountEvent implements AccountEvent {
    private String status;
}
