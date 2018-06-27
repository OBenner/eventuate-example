package ru.neoflex.customertransaction.backend;



import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.neoflex.customertransaction.dao.DaoConfig;
import ru.neoflex.customertransaction.tram.MessageService;
import ru.neoflex.customertransaction.tram.TransactionMessageConsumer;


import javax.sql.DataSource;

/**
 * The  Management configuration.
 */
@Configuration
@EnableAutoConfiguration
@Import({DaoConfig.class,TramJdbcKafkaConfiguration.class})
public class ConfigurationTransaction {


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


    /**
     * My consumer message consumer.
     *
     * @return the message consumer
     */
    @Bean
    public TransactionMessageConsumer myConsumer() {
        return new TransactionMessageConsumer();
    }

    /**
     * Message service message service.
     *
     * @param consumer        the consumer
     * @param messageConsumer the message consumer
     * @return the message service
     */
    @Bean
    public MessageService messageService(TransactionMessageConsumer consumer,
                                         io.eventuate.tram.messaging.consumer.MessageConsumer messageConsumer) {


        return new MessageService(consumer, messageConsumer);
    }
}
