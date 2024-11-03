package com.building.managment.system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/management/")
public class BuildingManagementController {

    @PostMapping("/buildings")
    public ResponseEntity<?> createBuilding(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<?> getBuildingDetails(@PathVariable  Long id){
        return ResponseEntity.ok().build();
    }
}
