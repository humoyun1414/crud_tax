package com.example.demo_crud_project.service;

import com.example.demo_crud_project.domain.Region;
import com.example.demo_crud_project.exp.RegionAlreadyExistsException;
import com.example.demo_crud_project.model.RegionDto;
import com.example.demo_crud_project.model.request.RegionRequest;
import com.example.demo_crud_project.repository.RegionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegionService {

    private final RegionRepository regionRepository;
    private final ObjectMapper objectMapper;

    public RegionService(RegionRepository regionRepository, ObjectMapper objectMapper) {
        this.regionRepository = regionRepository;
        this.objectMapper = objectMapper;
    }

    public RegionDto create(RegionRequest request) {
        if (regionRepository.findByName(request.name())
                .isPresent()) {
            throw new RegionAlreadyExistsException("This Region already exists!");
        }
        Region region = new Region();
        region.setName(request.name());
        Region savedRegion = regionRepository.save(region);
        return objectMapper.convertValue(savedRegion,RegionDto.class);
    }

    public PageImpl<RegionDto> findAll(int page, int size) {
        List<RegionDto> dtoList = new LinkedList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Region> regionPage = regionRepository.findAll(pageable);

        regionPage.forEach(region -> dtoList.add(objectMapper.convertValue(region,RegionDto.class)));
        return new PageImpl<>(dtoList, pageable, regionPage.getTotalPages());

    }

    public RegionDto findById(Integer id) {
        return regionRepository.findById(id)
                .map(region -> objectMapper.convertValue(region,RegionDto.class)).orElseThrow();
    }

    public Optional<RegionDto> update(Integer id, RegionRequest request) {
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isEmpty())
            throw new NoSuchElementException("Region not found");

        return regionOptional
                .map(region -> {
                    region.setName(request.name());
                    Region savedRegion = regionRepository.save(region);
                    return objectMapper.convertValue(savedRegion,RegionDto.class);
                });

    }

    public Boolean delete(Integer id) {
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isEmpty()) {
            throw new NoSuchElementException("Region not found");
        }
        regionRepository.deleteById(id);
        return true;
    }


}
