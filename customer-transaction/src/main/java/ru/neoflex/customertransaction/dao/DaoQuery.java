package ru.neoflex.customertransaction.dao;

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
import ru.neoflex.model.Transaction;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


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
     * New transaction.
     *
     * @param aggregateId the aggregate id
     * @param operation   the operation
     * @param amount      the amount
     */
    public void newTransaction(String aggregateId, String operation, String amount) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO account_transaction(aggregateId, operation, amount) VALUES (?,?,?)",
                    aggregateId, operation, amount);
            log.info("New Transaction");
        } catch (RuntimeException e) {
            log.info(e.getMessage());

        }
    }


    /**
     * Transaction list.
     *
     * @param aggregateId the aggregate id
     * @return the list
     */
    public List<Transaction> transactionList(String aggregateId) {
        logger.info("aggregateID {}", aggregateId);
        String sql = "SELECT * FROM account_transaction WHERE aggregateId =?";

        return jdbcTemplate.query(sql, new Object[]{aggregateId}, new RowMapper<Transaction>() {
            @Override
            public Transaction mapRow(ResultSet rs, int rownumber) throws SQLException {
                Transaction eventEntitys = new Transaction();
                eventEntitys.setAggregateId(rs.getString(1));
                eventEntitys.setOperation(rs.getString(2));
                eventEntitys.setAmount(rs.getString(3));
                logger.info(eventEntitys.toString());
                return eventEntitys;
            }
        });

    }
}
