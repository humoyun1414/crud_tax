package com.example.demo_crud_project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.example.demo_crud_project.util.Constants.TABLE_ORGANIZATION;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = TABLE_ORGANIZATION)
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer regionId;

    private Integer parent;
}
