package ru.neoflex.customer.backend.dao;

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
import ru.neoflex.model.CustomerEntity;

import javax.sql.DataSource;

/**
 * The type Dao query.
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
     * @param phone       the phone
     */
    public void insertEvent(String aggregateId, String phone) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO customer_table(aggregateId, phone) VALUES (?,?)",
                    aggregateId, phone);
            log.info("Insert new Account");
        } catch (RuntimeException e) {
            log.info(e.getMessage());

        }
    }

    /**
     * Find by phone management entity.
     *
     * @param phone the phone
     * @return the management entity
     */
    public CustomerEntity findByPhone(String phone) {
        String sql = "SELECT * FROM customer_table WHERE phone =?";
        log.info("findByPhone {}",sql);
        RowMapper<CustomerEntity> rowMapper = new BeanPropertyRowMapper<CustomerEntity>(CustomerEntity.class);
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, phone);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    /**
     * Change phone.
     *
     * @param phone       the phone
     * @param aggregateId the aggregate id
     */
    public void changePhone(String phone, String aggregateId) {
String sql ="UPDATE customer_table SET phone=? WHERE aggregateId=?";
        log.info("findByPhone {}",sql);
        jdbcTemplate.update(sql, phone, aggregateId);

    }

}
