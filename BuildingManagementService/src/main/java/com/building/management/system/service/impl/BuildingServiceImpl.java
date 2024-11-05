package com.building.management.system.service.impl;


import com.building.management.system.exception.BuildingAlreadyExistException;
import com.building.management.system.exception.ResourceNotFoundException;
import com.building.management.system.model.dto.BuildingDTO;
import com.building.management.system.service.BuildingService;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {


    /**
     * Creates a new building with the specified details.
     *
     * @param buildingDTO the data transfer object containing details for the new building
     * @return the created BuildingDTO, populated with generated IDs and any default values
     * @throws BuildingAlreadyExistException if building is already in db
     */
    @Override
    public BuildingDTO createBuilding(BuildingDTO buildingDTO) {
        return null;
    }

    /**
     * Retrieves the details of a specific building by its ID.
     *
     * @param id the ID of the building to retrieve
     * @return the BuildingDTO with details of the specified building
     * @throws ResourceNotFoundException if no building is found with the specified ID
     */
    @Override
    public BuildingDTO getBuildingDetails(Long id) {
        return null;
    }

    /**
     * Deletes a building from the system.
     *
     * @return true if the building was successfully deleted, false otherwise
     * @throws UnsupportedOperationException if deletion is not supported for certain buildings
     */
    @Override
    public boolean deleteBuilding() {
        return false;
    }
}
