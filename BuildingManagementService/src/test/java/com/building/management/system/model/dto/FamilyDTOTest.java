package com.building.management.system.model.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class FamilyDTOTest {
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidFamilyDTO() {
        FamilyDTO family = FamilyDTO.builder()
                .id(1L)
                .members(List.of(new PersonDTO()))
                .apartment(new ApartmentDTO())
                .build();

        Set<ConstraintViolation<FamilyDTO>> violations = validator.validate(family);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNullMembers() {
        FamilyDTO family = FamilyDTO.builder()
                .id(1L)
                .members(null)
                .apartment(new ApartmentDTO())
                .build();

        Set<ConstraintViolation<FamilyDTO>> violations = validator.validate(family);
        assertEquals(1, violations.size());
        assertEquals("Family members are required", violations.iterator().next().getMessage());
    }

    @Test
    public void testEmptyMembersList() {
        FamilyDTO family = FamilyDTO.builder()
                .id(1L)
                .members(new ArrayList<>())
                .apartment(new ApartmentDTO())
                .build();

        Set<ConstraintViolation<FamilyDTO>> violations = validator.validate(family);
        assertEquals(1, violations.size());
        assertEquals("A family must have at least one member", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullApartment() {
        FamilyDTO family = FamilyDTO.builder()
                .id(1L)
                .members(List.of(new PersonDTO()))
                .apartment(null)
                .build();

        Set<ConstraintViolation<FamilyDTO>> violations = validator.validate(family);
        assertEquals(1, violations.size());
        assertEquals("Apartment information is required", violations.iterator().next().getMessage());
    }
}
