package com.building.managment.system.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 4)
    private String number;
    @OneToOne(mappedBy = "apartment",cascade = CascadeType.ALL)
    private Family family;
    private Integer floor;
    private BigDecimal taxPerPerson;
}
