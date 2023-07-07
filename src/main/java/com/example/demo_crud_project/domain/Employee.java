package com.example.demo_crud_project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import static com.example.demo_crud_project.util.Constants.TABLE_EMPLOYEE;
import static com.example.demo_crud_project.util.Constants.TABLE_REGION;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = TABLE_EMPLOYEE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String pinfl;

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column(nullable = false)
    private Integer organizationId;

}
