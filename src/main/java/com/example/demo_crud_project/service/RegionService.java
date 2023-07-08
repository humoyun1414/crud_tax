package com.example.demo_crud_project.service;

import com.example.demo_crud_project.domain.Region;
import com.example.demo_crud_project.exp.ItemAlreadyExistsException;
import com.example.demo_crud_project.exp.ItemNotFoundException;
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
            throw new ItemAlreadyExistsException("This Region name already exists!");
        }
        Region region = new Region();
        region.setName(request.name());
        Region savedRegion = regionRepository.save(region);
        return objectMapper.convertValue(savedRegion, RegionDto.class);
    }

    public PageImpl<RegionDto> findAll(int page, int size) {
        List<RegionDto> dtoList = new LinkedList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Region> regionPage = regionRepository.findAll(pageable);

        regionPage.forEach(region -> dtoList.add(objectMapper.convertValue(region, RegionDto.class)));
        return new PageImpl<>(dtoList, pageable, regionPage.getTotalPages());

    }

    private Optional<Region> existById(Integer id) {
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isEmpty())
            throw new ItemNotFoundException("Region not found !");
        return regionOptional;
    }

    public RegionDto findById(Integer id) {
        return objectMapper.convertValue(existById(id).get(), RegionDto.class);
    }

    public Optional<RegionDto> update(Integer id, RegionRequest request) {
        return existById(id)
                .map(region -> {
                    region.setName(request.name());
                    Region savedRegion = regionRepository.save(region);
                    return objectMapper.convertValue(savedRegion, RegionDto.class);
                });

    }

    public Boolean delete(Integer id) {
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isEmpty()) {
            throw new ItemNotFoundException("Region not found");
        }
        regionRepository.deleteById(id);
        return true;
    }


}
