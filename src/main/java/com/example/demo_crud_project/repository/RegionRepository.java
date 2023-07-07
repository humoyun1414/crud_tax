package com.example.demo_crud_project.repository;

import com.example.demo_crud_project.domain.Region;
import com.example.demo_crud_project.model.RegionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByName(String name);
}
