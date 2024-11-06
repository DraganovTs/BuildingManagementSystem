package com.building.management.system.model.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RepairFundDTOTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidRepairFundDTO() {
        RepairFundDTO repairFundDTO = RepairFundDTO.builder()
                .balance(new BigDecimal("1000.00"))
                .transactions(Arrays.asList(new TransactionDTO()))
                .build();

        Set<ConstraintViolation<RepairFundDTO>> violations = validator.validate(repairFundDTO);
        assertEquals("There should be no validation errors", 0, violations.size());
    }

    @Test
    public void testBalanceCannotBeNull() {
        RepairFundDTO repairFundDTO = RepairFundDTO.builder()
                .balance(null)
                .transactions(Collections.emptyList())
                .build();

        Set<ConstraintViolation<RepairFundDTO>> violations = validator.validate(repairFundDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<RepairFundDTO> violation = violations.iterator().next();
        assertEquals("Transactions cannot be empty", violation.getMessage());
    }

    @Test
    public void testBalanceMustBeGreaterThanZero() {
        RepairFundDTO repairFundDTO = RepairFundDTO.builder()
                .balance(new BigDecimal("0.00"))
                .transactions(Collections.emptyList())
                .build();

        Set<ConstraintViolation<RepairFundDTO>> violations = validator.validate(repairFundDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<RepairFundDTO> violation = violations.iterator().next();
        assertEquals("Transactions cannot be empty", violation.getMessage());
    }

    @Test
    public void testTransactionsCannotBeNull() {
        RepairFundDTO repairFundDTO = RepairFundDTO.builder()
                .balance(new BigDecimal("500.00"))
                .transactions(null)
                .build();

        Set<ConstraintViolation<RepairFundDTO>> violations = validator.validate(repairFundDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<RepairFundDTO> violation = violations.iterator().next();
        assertEquals("Transactions cannot be empty", violation.getMessage());
    }

    @Test
    public void testTransactionsSizeCannotBeZero() {
        RepairFundDTO repairFundDTO = RepairFundDTO.builder()
                .balance(new BigDecimal("500.00"))
                .transactions(Collections.emptyList())
                .build();

        Set<ConstraintViolation<RepairFundDTO>> violations = validator.validate(repairFundDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<RepairFundDTO> violation = violations.iterator().next();
        assertEquals("Transactions cannot be empty", violation.getMessage());
    }

    @Test
    public void testRepairFundDTOWithValidTransactions() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(new BigDecimal("200.00"))
                .description("Repair transaction")
                .build();

        RepairFundDTO repairFundDTO = RepairFundDTO.builder()
                .balance(new BigDecimal("1000.00"))
                .transactions(Collections.singletonList(transactionDTO))
                .build();

        Set<ConstraintViolation<RepairFundDTO>> violations = validator.validate(repairFundDTO);
        assertEquals("There should be no validation errors", 0, violations.size());
    }
}
