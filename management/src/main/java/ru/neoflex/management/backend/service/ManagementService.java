package ru.neoflex.management.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.neoflex.management.backend.dao.DaoQuery;
import ru.neoflex.management.web.NotFound;
import ru.neoflex.model.ManagementEntity;
import ru.neoflex.model.Transaction;

import java.util.List;

/**
 * The  Management service.
 */
@Service
public class ManagementService {
    private static final Logger logger = LoggerFactory.getLogger(ManagementService.class);
    @Autowired
    private DaoQuery query;


    /**
     * Confirm response entity.
     *
     * @param aggregateId the aggregate id
     * @return the response entity
     */
    public ResponseEntity confirm(String aggregateId) {
        ManagementEntity entity = query.findStatusByAggregateId(aggregateId);
        logger.info("updateEntity {}",entity);
        if (entity == null) {
            throw new NotFound("Account not found");
        }
        String newStatus = "confirm";
        query.changeStatus(newStatus,aggregateId);
        ManagementEntity updateEntity = query.findStatusByAggregateId(aggregateId);
        logger.info("updateEntity {}",updateEntity);
        return new ResponseEntity(updateEntity,HttpStatus.OK);
    }

    /**
     * Gets status.
     *
     * @param aggregateId the aggregate id
     * @return the status
     */
    public ResponseEntity getStatus(String aggregateId) {
        ManagementEntity entity = query.findStatusByAggregateId(aggregateId);
        logger.info("updateEntity {}",entity);
        if (entity == null) {
            throw new NotFound("Account not found");
        }

        return new ResponseEntity(entity,HttpStatus.OK);
    }




}
