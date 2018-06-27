package ru.neoflex.customer.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.neoflex.customer.backend.model.CreateCustomerEntity;

/**
 * The  Create account command.
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateAccountCommand implements AccountCommand{
    private String name;
    private double amount;
    private String status;
    private String phone;


    /**
     * Instantiates a new Create account command.
     *
     * @param customerEntity the customer entity
     */
    public CreateAccountCommand(CreateCustomerEntity customerEntity) {
        this.name=customerEntity.getName();
        this.phone=customerEntity.getPhone();
        this.amount = 0;
        this.status = "created";
    }
}
