package ru.neoflex.customer.backend.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * The  Dao config.
 */
@Configuration
public class DaoConfig {
    /**
     * Query data source.
     *
     * @return the data source
     */
    @Bean
    @Qualifier("queryDataSource")
    @ConfigurationProperties("datasource.query")
    public DataSource queryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Query jdbc template.
     *
     * @param queryDataSource the query data source
     * @return the jdbc template
     */
    @Bean
    @Qualifier("queryJdbcTemplate")
    JdbcTemplate queryJdbcTemplate(@Qualifier("queryDataSource")DataSource queryDataSource) {
        return new JdbcTemplate(queryDataSource);
    }

    /**
     * Query data source properties.
     *
     * @return the data source properties
     */
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties queryDataSourceProperties() {
        return new DataSourceProperties();
    }


    /**
     * Eventuate data source.
     *
     * @return the data source
     */
    @Primary
    @Bean
    public DataSource eventuateDataSource() {
        return queryDataSourceProperties().initializeDataSourceBuilder().build();
    }

}
