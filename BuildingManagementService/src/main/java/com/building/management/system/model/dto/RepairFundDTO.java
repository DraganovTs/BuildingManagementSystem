package com.building.management.system.model.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepairFundDTO {
    private Long id;

    @DecimalMin(value = "0.0", message = "Balance cannot be negative")
    private BigDecimal balance;

    @NotEmpty(message = "Transactions cannot be empty")
    private List<TransactionDTO> transactions;
}

