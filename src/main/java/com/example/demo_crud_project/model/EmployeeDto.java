package com.example.demo_crud_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String pinfl;
    private LocalDate hireDate;
    private Integer organizationId;
}
