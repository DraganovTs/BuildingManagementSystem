package com.building.managment.system.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 1, max = 50)
    private String address;
    @OneToMany
    private List<Apartment> apartments = new ArrayList<>();
    @OneToOne
    private RepairFund repairFound = new RepairFund();
    private BigDecimal managementFee;

}
