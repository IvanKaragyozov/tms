package pu.master.tmsapi.controllers;


import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pu.master.tmsapi.models.dtos.RightDto;
import pu.master.tmsapi.models.entities.Right;
import pu.master.tmsapi.models.requests.RightRequest;
import pu.master.tmsapi.services.RightService;


@RestController
public class RightController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RightController.class);

    private final RightService rightService;


    @Autowired
    public RightController(final RightService rightService)
    {
        this.rightService = rightService;
    }


    @PostMapping("/rights")
    public ResponseEntity<Void> createRight(@RequestBody @Valid final RightRequest rightRequest)
    {
        LOGGER.debug("Trying to save right to the database");
        final Right right = this.rightService.createRight(rightRequest);
        LOGGER.info("Created new right");

        final URI location = UriComponentsBuilder.fromUriString("/rights/{id}")
                                                 .buildAndExpand(right.getId())
                                                 .toUri();

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/rights")
    public ResponseEntity<List<RightDto>> getAllRights()
    {
        final List<RightDto> allRights = this.rightService.getAllRights();
        LOGGER.info("Requesting all roles from the database");

        return ResponseEntity.ok(allRights);
    }

}
