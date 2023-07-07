package com.example.demo_crud_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateDtoCondition3 {
    private Integer id;
    private String firstname;
    private LocalDate hireDate;
    private String lastname;
    private Integer organizationId;
    private String pinfl;
    private String organizationName;
    private Double avarageSalary;
}
