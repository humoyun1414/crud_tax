package com.example.demo_crud_project.model;

import com.example.demo_crud_project.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private Integer id;
    private String name;
    private Integer regionId;
    private Integer parent;
}
