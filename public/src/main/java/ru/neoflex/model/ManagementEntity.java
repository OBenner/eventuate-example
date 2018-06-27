package ru.neoflex.model;

import lombok.*;

/**
 * The type Management entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagementEntity {
    private String aggregateId;
    private String account_status;
}
