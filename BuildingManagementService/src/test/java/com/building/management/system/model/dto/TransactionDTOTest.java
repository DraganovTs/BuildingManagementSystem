package com.building.management.system.model.dto;

import com.building.management.system.model.enums.PaymentMethod;
import com.building.management.system.model.enums.TransactionType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TransactionDTOTest {
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidTransactionDTO() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(new BigDecimal("100.00"))
                .date(LocalDate.now())
                .type(TransactionType.INCOME)
                .description("Transaction description")
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .build();

        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
        assertEquals("There should be no validation errors", 0, violations.size());
    }

    @Test
    public void testAmountCannotBeZero() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(BigDecimal.ZERO)
                .date(LocalDate.now())
                .type(TransactionType.INCOME)
                .description("Transaction description")
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .build();

        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<TransactionDTO> violation = violations.iterator().next();
        assertEquals("Amount must be positive", violation.getMessage());
    }



    @Test
    public void testDateCannotBeNull() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(new BigDecimal("100.00"))
                .date(null)
                .type(TransactionType.INCOME)
                .description("Transaction description")
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .build();

        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<TransactionDTO> violation = violations.iterator().next();
        assertEquals("Date is required", violation.getMessage());
    }

    @Test
    public void testDateCannotBeInTheFuture() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(new BigDecimal("100.00"))
                .date(LocalDate.now().plusDays(1))
                .type(TransactionType.INCOME)
                .description("Transaction description")
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .build();

        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<TransactionDTO> violation = violations.iterator().next();
        assertEquals("Transaction date cannot be in the future", violation.getMessage());
    }


    @Test
    public void testTransactionTypeInvalidEnum() {
        try {
            TransactionDTO transactionDTO = TransactionDTO.builder()
                    .amount(new BigDecimal("100.00"))
                    .date(LocalDate.now())
                    .type(TransactionType.valueOf("INVALID"))  // This will throw an IllegalArgumentException
                    .description("Transaction description")
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .build();
        } catch (IllegalArgumentException e) {
            assertEquals("No enum constant com.building.management.system.model.enums.TransactionType.INVALID", e.getMessage());
        }
    }

    @Test
    public void testDescriptionCannotBeBlank() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(new BigDecimal("100.00"))
                .date(LocalDate.now())
                .type(TransactionType.INCOME)
                .description("")
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .build();

        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<TransactionDTO> violation = violations.iterator().next();
        assertEquals("Description cannot be blank", violation.getMessage());
    }

    @Test
    public void testDescriptionTooLong() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(new BigDecimal("100.00"))
                .date(LocalDate.now())
                .type(TransactionType.INCOME)
                .description("A".repeat(256))
                .paymentMethod(PaymentMethod.CREDIT_CARD)
                .build();

        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
        assertEquals("There should be one validation error", 1, violations.size());

        ConstraintViolation<TransactionDTO> violation = violations.iterator().next();
        assertEquals("Description length must be less than 255 characters", violation.getMessage());
    }

    @Test
    public void testPaymentMethodCannotBeNull() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(new BigDecimal("100.00"))
                .date(LocalDate.now())
                .type(TransactionType.INCOME)
                .description("Transaction description")
                .build();

        Set<ConstraintViolation<TransactionDTO>> violations = validator.validate(transactionDTO);
        assertEquals("There should be one validation error", 2, violations.size());

        ConstraintViolation<TransactionDTO> violation = violations.iterator().next();
        assertEquals("Payment method is required", violation.getMessage());
    }

    @Test
    public void testPaymentMethodInvalidEnum() {
        try {
            TransactionDTO transactionDTO = TransactionDTO.builder()
                    .amount(new BigDecimal("100.00"))
                    .date(LocalDate.now())
                    .type(TransactionType.INCOME)
                    .description("Transaction description")
                    .paymentMethod(PaymentMethod.valueOf("INVALID"))  // This will throw an IllegalArgumentException
                    .build();
        } catch (IllegalArgumentException e) {
            assertEquals("No enum constant com.building.management.system.model.enums.PaymentMethod.INVALID", e.getMessage());
        }
    }
}
