package com.example.demo_crud_project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.example.demo_crud_project.util.Constants.TABLE_REGION;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = TABLE_REGION)
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
}
