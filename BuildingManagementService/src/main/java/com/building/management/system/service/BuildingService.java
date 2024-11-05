package com.building.management.system.service;

import com.building.management.system.exception.BuildingAlreadyExistException;
import com.building.management.system.exception.ResourceNotFoundException;
import com.building.management.system.model.dto.BuildingDTO;

/**
 * Service interface for managing buildings in the building management system.
 */
public interface BuildingService {

    /**
     * Creates a new building with the specified details.
     *
     * @param buildingDTO the data transfer object containing details for the new building
     * @return the created BuildingDTO, populated with generated IDs and any default values
     * @throws BuildingAlreadyExistException if building is already in db
     */
    BuildingDTO createBuilding(BuildingDTO buildingDTO);

    /**
     * Retrieves the details of a specific building by its ID.
     *
     * @param id the ID of the building to retrieve
     * @return the BuildingDTO with details of the specified building
     * @throws ResourceNotFoundException if no building is found with the specified ID
     */
    BuildingDTO getBuildingDetails(Long id);

    /**
     * Deletes a building from the system.
     *
     * @return true if the building was successfully deleted, false otherwise
     * @throws UnsupportedOperationException if deletion is not supported for certain buildings
     */
    boolean deleteBuilding();
}