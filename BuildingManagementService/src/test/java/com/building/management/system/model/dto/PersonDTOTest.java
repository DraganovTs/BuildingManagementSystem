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

public class PersonDTOTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidPersonDTO() {
        PersonDTO person = PersonDTO.builder()
                .id(1L)
                .name("John Doe")
                .isChild(false)
                .hasPet(true)
                .discount(new BigDecimal("10.0"))
                .build();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        assertEquals(0, violations.size());
    }

    @Test
    public void testNullName() {
        PersonDTO person = PersonDTO.builder()
                .id(1L)
                .name(null)
                .isChild(false)
                .hasPet(true)
                .discount(new BigDecimal("10.0"))
                .build();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("Name cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    public void testNameTooShort() {
        PersonDTO person = PersonDTO.builder()
                .id(1L)
                .name("J")
                .isChild(false)
                .hasPet(true)
                .discount(new BigDecimal("10.0"))
                .build();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("Name length must be between 2 and 20 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testNameTooLong() {
        PersonDTO person = PersonDTO.builder()
                .id(1L)
                .name("Johnathan Doe the Third")
                .isChild(false)
                .hasPet(true)
                .discount(new BigDecimal("10.0"))
                .build();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("Name length must be between 2 and 20 characters", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullIsChild() {
        PersonDTO person = PersonDTO.builder()
                .id(1L)
                .name("John Doe")
                .isChild(null)
                .hasPet(true)
                .discount(new BigDecimal("10.0"))
                .build();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("Please specify if the person is a child", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullHasPet() {
        PersonDTO person = PersonDTO.builder()
                .id(1L)
                .name("John Doe")
                .isChild(false)
                .hasPet(null)
                .discount(new BigDecimal("10.0"))
                .build();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("Please specify if the person has a pet", violations.iterator().next().getMessage());
    }

    @Test
    public void testNegativeDiscount() {
        PersonDTO person = PersonDTO.builder()
                .id(1L)
                .name("John Doe")
                .isChild(false)
                .hasPet(true)
                .discount(new BigDecimal("-5.0"))
                .build();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        assertEquals(1, violations.size());
        assertEquals("Discount cannot be negative", violations.iterator().next().getMessage());
    }

    @Test
    public void testValidDiscount() {
        PersonDTO person = PersonDTO.builder()
                .id(1L)
                .name("John Doe")
                .isChild(false)
                .hasPet(true)
                .discount(new BigDecimal("10.0"))
                .build();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        assertEquals(0, violations.size());
    }
}
