package com.example.demo_crud_project.repository;

import com.example.demo_crud_project.domain.CalculationTable;
import com.example.demo_crud_project.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
