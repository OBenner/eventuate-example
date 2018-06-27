package ru.neoflex.management.backend;


import io.eventuate.javaclient.spring.EnableEventHandlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.neoflex.management.backend.dao.DaoConfig;
import ru.neoflex.management.eventHandler.EventHandlerManagement;


import javax.sql.DataSource;

/**
 * The  Management configuration.
 */
@Configuration
@EnableEventHandlers
@Import({DaoConfig.class})
public class ManagementConf {

    /**
     * Event handler.
     *
     * @return the event handler
     */
    @Bean
    public EventHandlerManagement eventHandler() {
        return new EventHandlerManagement();
    }


    /**
     * Jdbc template jdbc template.
     *
     * @param dataSource the data source
     * @return the jdbc template
     */
    @Primary
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
