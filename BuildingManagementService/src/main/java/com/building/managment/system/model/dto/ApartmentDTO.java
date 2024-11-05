package com.building.managment.system.model.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentDTO {
    private Long id;
    @NotNull(message = "Apartment number cannot be null")
    @Pattern(regexp = "\\d+[A-Za-z]*", message = "Invalid apartment number format")
    private String number;

    @NotNull(message = "Family information is required")
    private FamilyDTO family;

    @NotNull(message = "Floor cannot be null")
    @Min(value = 0, message = "Floor number cannot be negative")
    private Integer floor;

    @DecimalMin(value = "0.0", inclusive = false, message = "Tax per person must be positive")
    private BigDecimal taxPerPerson;
}
