package ru.neoflex.customer;


import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;
import ru.neoflex.customer.command.*;
import ru.neoflex.event.CreateEvent;
import ru.neoflex.event.UpdateAmountAccountEvent;
import ru.neoflex.event.UpdatePhoneAccountEvent;
import ru.neoflex.event.UpdateStatusAccountEvent;
import ru.neoflex.model.Account;

import java.util.List;

/**
 * The type Account aggregate.
 */
public class AccountAggregate extends ReflectiveMutableCommandProcessingAggregate<AccountAggregate, AccountCommand> {


    @Getter
    private Account account = new Account();

    /**
     * Create Account Event process.
     *
     * @param cmd the cmd
     * @return the list
     */
    public List<Event> process(CreateAccountCommand cmd) {
        return EventUtil.events(new CreateEvent(
                cmd.getName(),
                cmd.getAmount(),
                cmd.getStatus(),
                cmd.getPhone()));
    }


    /**
     *Create Account Event Apply.
     *
     * @param event the event
     */
    public void apply(CreateEvent event) {
        this.account.setName(event.getName());
        this.account.setAmount(event.getAmount());
        this.account.setStatus(event.getStatus());
        this.account.setPhone(event.getPhone());
    }


    /**
     * Update Amount Account Event process.
     *
     * @param cmd the cmd
     * @return the list
     */
    public List<Event> process(UpdateAmountAccountCommand cmd) {
        return EventUtil.events(new UpdateAmountAccountEvent(
                cmd.getAmount(),
                cmd.getOperation()));
    }

    /**
     *Update Amount Account Event Apply.
     *
     * @param event the event
     */
    public void apply(UpdateAmountAccountEvent event) {
        this.account.setLastOperation(event.getOperation());
        this.account.setAmount(event.getAmount());
    }


    /**
     * Update Status Account Event process.
     *
     * @param cmd the cmd
     * @return the list
     */
    public List<Event> process(UpdateStatusAccountCommand cmd) {
        return EventUtil.events(new UpdateStatusAccountEvent(
                cmd.getStatus()));
    }

    /**
     * Update Status Account Event  Apply.
     *
     * @param event the event
     */
    public void apply(UpdateStatusAccountEvent event) {
        this.account.setStatus(event.getStatus());
    }


    /**
     * Update Phone Account Event process.
     *
     * @param cmd the cmd
     * @return the list
     */
    public List<Event> process(UpdatePhoneAccountCommand cmd) {
        return EventUtil.events(new UpdatePhoneAccountEvent(
                cmd.getPhone()));
    }

    /**
     *  Update Phone Account Event Apply.
     *
     * @param event the event
     */
    public void apply(UpdatePhoneAccountEvent event) {
        this.account.setPhone(event.getPhone());
    }
}
