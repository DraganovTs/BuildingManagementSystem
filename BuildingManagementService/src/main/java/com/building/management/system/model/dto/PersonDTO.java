package com.building.management.system.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 20, message = "Name length must be between 2 and 20 characters")
    private String name;

    @NotNull(message = "Please specify if the person is a child")
    private Boolean isChild;

    @NotNull(message = "Please specify if the person has a pet")
    private Boolean hasPet;

    @DecimalMin(value = "0.0",  message = "Discount cannot be negative")
    private BigDecimal discount;
}
