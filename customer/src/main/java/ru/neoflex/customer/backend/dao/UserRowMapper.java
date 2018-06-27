package ru.neoflex.customer.backend.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.neoflex.model.CustomerEntity;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User row mapper.
 */
public class UserRowMapper implements RowMapper<CustomerEntity> {

    @Override
    public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerEntity entity = new CustomerEntity();
        entity.setAggregateId(rs.getString("aggregateId"));
        entity.setPhone(rs.getString("phone"));
        return entity;
    }
}
