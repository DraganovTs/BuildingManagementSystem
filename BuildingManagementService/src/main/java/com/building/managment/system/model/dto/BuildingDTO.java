package com.building.managment.system.model.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class BuildingDTO {
    private Long id;

    @NotNull(message = "Address cannot be null")
    @Size(min = 5, max = 50, message = "Address length must be between 5 and 50 characters")
    private String address;

    @NotEmpty(message = "A building must contain at least one apartment")
    private List<ApartmentDTO> apartments;

    private RepairFundDTO repairFund;

    @DecimalMin(value = "0.0", inclusive = false, message = "Management fee must be positive")
    private BigDecimal managementFee;
}
