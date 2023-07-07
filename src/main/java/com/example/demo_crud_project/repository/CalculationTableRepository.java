package com.example.demo_crud_project.repository;

import com.example.demo_crud_project.domain.CalculationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CalculationTableRepository extends JpaRepository<CalculationTable, Integer> {
}
