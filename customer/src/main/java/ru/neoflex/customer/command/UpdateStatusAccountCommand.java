package ru.neoflex.customer.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The  Update status account command.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusAccountCommand implements AccountCommand {
    private String status;
}
