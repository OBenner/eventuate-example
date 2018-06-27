package ru.neoflex.management.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.management.backend.service.ManagementService;

/**
 * The Customer Controller.
 */
@RestController
@RequestMapping(value = "management")
public class ManagementController {

    @Autowired
    private ManagementService service;


    /**
     * Confirm account response entity.
     *
     * @param aggregateId the aggregate id
     * @return the response entity
     */
    @GetMapping
    @RequestMapping(value = "/confirm/{aggregateId}")
    public ResponseEntity confirmAccount(@PathVariable String aggregateId) {
        return service.confirm(aggregateId);
    }

    /**
     * Gets status info.
     *
     * @param aggregateId the aggregate id
     * @return the status info
     */
    @GetMapping
    @RequestMapping(value = "/status/info/{aggregateId}")
    public ResponseEntity getStatusInfo(@PathVariable String aggregateId) {
        return service.confirm(aggregateId);
    }





}
