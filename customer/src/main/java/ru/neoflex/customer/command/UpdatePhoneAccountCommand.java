package ru.neoflex.customer.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Update phone account command.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhoneAccountCommand implements AccountCommand{
    private String phone;
}
