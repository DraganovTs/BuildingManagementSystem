package com.building.management.system.controller;

import com.building.management.system.model.dto.BuildingDTO;
import com.building.management.system.service.impl.BuildingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/management")
public class BuildingManagementController {

    private final BuildingServiceImpl buildingService;

    public BuildingManagementController(BuildingServiceImpl buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping("/buildings")
    public ResponseEntity<BuildingDTO> createBuilding(@Valid @RequestBody BuildingDTO buildingDTO) {
        BuildingDTO createdBuildingDTO = buildingService.createBuilding(buildingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBuildingDTO);
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<BuildingDTO> getBuildingDetails(@PathVariable Long id) {
        BuildingDTO fetchBuildingDTO = buildingService.getBuildingDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(fetchBuildingDTO);
    }

    @DeleteMapping("/buildings/{id}")
    public ResponseEntity<BuildingDTO> deleteBuilding(@PathVariable Long id) {
        boolean isDeleted = buildingService.deleteBuilding();
        return isDeleted
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public String getHello(){
        return "Hello World";
    }
}
