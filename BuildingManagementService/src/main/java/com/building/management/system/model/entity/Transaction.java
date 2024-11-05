package com.building.management.system.model.entity;

import com.building.management.system.model.enums.PaymentMethod;
import com.building.management.system.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionType type;
    private String description;
    @ManyToOne
    private Apartment apartment;
    @ManyToOne
    private Building building;
    private PaymentMethod paymentMethod;

}
