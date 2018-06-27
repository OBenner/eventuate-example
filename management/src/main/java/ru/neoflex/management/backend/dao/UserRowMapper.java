package ru.neoflex.management.backend.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.neoflex.model.ManagementEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User row mapper.
 */
public class UserRowMapper implements RowMapper<ManagementEntity> {

    @Override
    public ManagementEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ManagementEntity entity = new ManagementEntity();
        entity.setAggregateId(rs.getString("aggregateId"));
        entity.setAccount_status(rs.getString("account_status"));
        return entity;
    }
}
