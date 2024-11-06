package com.building.management.system.model.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ApartmentDTOTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidApartmentDTO() {
        ApartmentDTO apartment = ApartmentDTO.builder()
                .id(1L)
                .number("101A")
                .family(new FamilyDTO())
                .floor(2)
                .taxPerPerson(new BigDecimal("100.0"))
                .build();

        Set<ConstraintViolation<ApartmentDTO>> violations = validator.validate(apartment);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNullApartmentNumber() {
        ApartmentDTO apartment = ApartmentDTO.builder()
                .id(1L)
                .number(null)
                .family(new FamilyDTO())
                .floor(2)
                .taxPerPerson(new BigDecimal("100.0"))
                .build();

        Set<ConstraintViolation<ApartmentDTO>> violations = validator.validate(apartment);
        assertEquals(1, violations.size());
        assertEquals("Apartment number cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidApartmentNumberFormat() {
        ApartmentDTO apartment = ApartmentDTO.builder()
                .id(1L)
                .number("!101")  // Invalid format
                .family(new FamilyDTO())
                .floor(2)
                .taxPerPerson(new BigDecimal("100.0"))
                .build();

        Set<ConstraintViolation<ApartmentDTO>> violations = validator.validate(apartment);
        assertEquals(1, violations.size());
        assertEquals("Invalid apartment number format", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullFamily() {
        ApartmentDTO apartment = ApartmentDTO.builder()
                .id(1L)
                .number("101A")
                .family(null)
                .floor(2)
                .taxPerPerson(new BigDecimal("100.0"))
                .build();

        Set<ConstraintViolation<ApartmentDTO>> violations = validator.validate(apartment);
        assertEquals(1, violations.size());
        assertEquals("Family information is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullFloor() {
        ApartmentDTO apartment = ApartmentDTO.builder()
                .id(1L)
                .number("101A")
                .family(new FamilyDTO())
                .floor(null)
                .taxPerPerson(new BigDecimal("100.0"))
                .build();

        Set<ConstraintViolation<ApartmentDTO>> violations = validator.validate(apartment);
        assertEquals(1, violations.size());
        assertEquals("Floor cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    public void testNegativeFloor() {
        ApartmentDTO apartment = ApartmentDTO.builder()
                .id(1L)
                .number("101A")
                .family(new FamilyDTO())
                .floor(-1)
                .taxPerPerson(new BigDecimal("100.0"))
                .build();

        Set<ConstraintViolation<ApartmentDTO>> violations = validator.validate(apartment);
        assertEquals(1, violations.size());
        assertEquals("Floor number cannot be negative", violations.iterator().next().getMessage());
    }

    @Test
    public void testZeroTaxPerPerson() {
        ApartmentDTO apartment = ApartmentDTO.builder()
                .id(1L)
                .number("101A")
                .family(new FamilyDTO())
                .floor(2)
                .taxPerPerson(new BigDecimal("0.0"))
                .build();

        Set<ConstraintViolation<ApartmentDTO>> violations = validator.validate(apartment);
        assertEquals(1, violations.size());
        assertEquals("Tax per person must be positive", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullTaxPerPerson() {
        ApartmentDTO apartment = ApartmentDTO.builder()
                .id(1L)
                .number("101A")
                .family(new FamilyDTO())
                .floor(2)
                .taxPerPerson(null)
                .build();

        Set<ConstraintViolation<ApartmentDTO>> violations = validator.validate(apartment);
        assertEquals(0, violations.size());
    }
}
