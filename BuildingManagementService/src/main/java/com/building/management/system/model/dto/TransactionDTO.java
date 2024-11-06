package com.building.management.system.model.dto;


import com.building.management.system.model.enums.PaymentMethod;
import com.building.management.system.model.enums.TransactionType;
import com.building.management.system.validation.ValidEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private Long id;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Transaction date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "Transaction type is required")
    @Enumerated(EnumType.STRING)
    @ValidEnum(enumClass = TransactionType.class,message = "invalid transaction type")
    private TransactionType type;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description length must be less than 255 characters")
    private String description;

    private ApartmentDTO apartment;

    private BuildingDTO building;

    @NotNull(message = "Payment method is required")
    @Enumerated(EnumType.STRING)
    @ValidEnum(enumClass = PaymentMethod.class,message = "invalid payment method")
    private PaymentMethod paymentMethod;
}

