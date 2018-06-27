package ru.neoflex.tramMessage;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Map;

/**
 * The  Manage message for tram.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@ToString
public class ManageMessage {
    private Map<String, String> message;
    private ArrayList<String> additionalInfo;

    /**
     * Instantiates a new Manage message.
     *
     * @param message the message
     */
    public ManageMessage(Map<String, String> message) {
        this.message = message;
    }
}
