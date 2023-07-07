package com.example.demo_crud_project.model;

import com.example.demo_crud_project.util.CalculationTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationTableDto {
    private Integer id;
    private Integer employeeId;
    private Double amount;
    private int rate;
    private LocalDate date;
    private Integer organizationId;
    private CalculationTypes calculationType;
}
