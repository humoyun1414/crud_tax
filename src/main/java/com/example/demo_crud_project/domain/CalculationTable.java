package com.example.demo_crud_project.domain;

import com.example.demo_crud_project.util.CalculationTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import static com.example.demo_crud_project.util.Constants.TABLE_CALCULATION;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = TABLE_CALCULATION)
public class CalculationTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer employeeId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private int rate;                                 // (ish stavkasi)

    private LocalDate date = LocalDate.now();         //(yyyy.mm.dd)
    @Column(nullable = false)
    private Integer organizationId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CalculationTypes calculationType;
}
