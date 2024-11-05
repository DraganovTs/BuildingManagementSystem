package com.building.managment.system.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyDTO {
    private Long id;

    @NotNull(message = "Family members are required")
    @Size(min = 1, message = "A family must have at least one member")
    private List<PersonDTO> members;

    @NotNull(message = "Apartment information is required")
    private ApartmentDTO apartment;
}
