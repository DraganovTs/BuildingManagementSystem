package com.building.management.system.model.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BuildingDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidBuildingDTO() {
        BuildingDTO building = new BuildingDTO();
        building.setAddress("123 Main Street");
        building.setManagementFee(new BigDecimal("100.00"));
        building.setApartments(new ArrayList<>());
        building.getApartments().add(new ApartmentDTO());

        Set<ConstraintViolation<BuildingDTO>> violations = validator.validate(building);
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidAddressLength() {
        BuildingDTO building = new BuildingDTO();
        building.setAddress("123"); // Too short
        building.setManagementFee(new BigDecimal("100.00"));
        building.setApartments(new ArrayList<>());
        building.getApartments().add(new ApartmentDTO());

        Set<ConstraintViolation<BuildingDTO>> violations = validator.validate(building);
        assertEquals(1, violations.size());
        assertEquals("Address length must be between 5 and 50 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testNotPositiveManagementFee() {
        BuildingDTO building = new BuildingDTO();
        building.setAddress("123 Main Street");
        building.setApartments(new ArrayList<>());
        building.getApartments().add(new ApartmentDTO());
        building.setManagementFee(new BigDecimal("-100.00"));

        Set<ConstraintViolation<BuildingDTO>> violations = validator.validate(building);
        assertEquals(1, violations.size());

        ConstraintViolation<BuildingDTO> violation = violations.iterator().next();
        assertEquals("Management fee must be positive", violation.getMessage());
    }

    @Test
    public void testNullManagementFee(){
        BuildingDTO building = new BuildingDTO();
        building.setAddress("123 Main Street");
        building.setApartments(new ArrayList<>());
        building.getApartments().add(new ApartmentDTO());

        Set<ConstraintViolation<BuildingDTO>> violations = validator.validate(building);
        assertEquals(1, violations.size());

        ConstraintViolation<BuildingDTO> violation = violations.iterator().next();
        assertEquals("Management Fee must be not null", violation.getMessage());

    }
}
