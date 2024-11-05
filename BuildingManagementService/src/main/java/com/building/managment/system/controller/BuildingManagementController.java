package com.building.managment.system.controller;

import com.building.managment.system.model.dto.BuildingDTO;
import com.building.managment.system.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/management/")
public class BuildingManagementController {

    private final BuildingService buildingService;

    public BuildingManagementController(BuildingService buildingService) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<BuildingDTO> deleteBuilding(@PathVariable Long id) {
        boolean isDeleted = buildingService.deleteBuilding();
        return isDeleted
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
