package ru.neoflex.customer.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Update amount account command.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAmountAccountCommand implements AccountCommand{
    private double amount;
    private String operation;
}
