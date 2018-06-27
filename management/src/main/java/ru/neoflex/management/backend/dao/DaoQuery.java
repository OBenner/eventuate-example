package ru.neoflex.management.backend.dao;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.neoflex.model.ManagementEntity;


import javax.sql.DataSource;


/**
 * The Dao query.
 */
@Repository
@Slf4j
public class DaoQuery {
    private static final Logger logger = LoggerFactory.getLogger(DaoQuery.class);
    @Autowired
    @Qualifier("queryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * The Data source.
     */
    @Autowired
    @Qualifier("queryDataSource")
    DataSource dataSource;


    /**
     * Insert event.
     *
     * @param aggregateId the aggregate id
     * @param status      the status
     */
    public void insertEvent(String aggregateId, String status) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO management_table(aggregateId, account_status) VALUES (?,?)",
                    aggregateId, status);
            log.info("Insert new Account");
        } catch (RuntimeException e) {
            log.info(e.getMessage());

        }
    }


    /**
     * Find status by aggregate id management entity.
     *
     * @param aggregateId the aggregate id
     * @return the management entity
     */
    public ManagementEntity findStatusByAggregateId(String aggregateId) {
        String sql = "SELECT * FROM management_table WHERE aggregateId =(?)";
        RowMapper<ManagementEntity> rowMapper = new BeanPropertyRowMapper<ManagementEntity>(ManagementEntity.class);
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, aggregateId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Change status.
     *
     * @param account_status the account status
     * @param aggregateId    the aggregate id
     */
    public void changeStatus(String account_status, String aggregateId) {

        jdbcTemplate.update("UPDATE management_table set account_status = ?  WHERE aggregateId=?", account_status, aggregateId);

    }



}
