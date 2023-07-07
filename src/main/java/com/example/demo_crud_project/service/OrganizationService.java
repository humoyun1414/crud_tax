package com.example.demo_crud_project.service;

import com.example.demo_crud_project.domain.Organization;
import com.example.demo_crud_project.model.OrganizationDto;
import com.example.demo_crud_project.model.request.OrganizationRequest;
import com.example.demo_crud_project.repository.OrganizationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ObjectMapper objectMapper;

    public OrganizationService(OrganizationRepository organizationRepository, ObjectMapper objectMapper) {
        this.organizationRepository = organizationRepository;
        this.objectMapper = objectMapper;
    }


    public OrganizationDto create(OrganizationRequest request) {

        Organization organization = objectMapper.convertValue(request, Organization.class);
        Organization savedOrganization = organizationRepository.save(organization);
        return objectMapper.convertValue(savedOrganization, OrganizationDto.class);
    }

    public PageImpl<OrganizationDto> findAll(int page, int size) {
        List<OrganizationDto> dtoList = new LinkedList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Organization> organizationPage = organizationRepository.findAll(pageable);

        organizationPage.forEach(organization -> dtoList.add(objectMapper.convertValue(organization, OrganizationDto.class)));
        return new PageImpl<>(dtoList, pageable, organizationPage.getTotalPages());

    }

    public OrganizationDto findById(Integer id) {
        return organizationRepository.findById(id)
                .map(organization -> objectMapper.convertValue(organization, OrganizationDto.class)).orElseThrow();
    }

    public Optional<OrganizationDto> update(Integer id, OrganizationRequest request) {
        Optional<Organization> organizationOptional = organizationRepository.findById(id);
        if (organizationOptional.isEmpty())
            throw new NoSuchElementException("Organization not found");

        return organizationOptional
                .map(organization -> {
                    organization.setName(request.name());
                    organization.setParent(request.parent());
                    organization.setRegionId(request.regionId());
                    Organization savedOrganization = organizationRepository.save(organization);
                    return objectMapper.convertValue(savedOrganization, OrganizationDto.class);
                });

    }

    public Boolean delete(Integer id) {
        Optional<Organization> organizationOptional = organizationRepository.findById(id);
        if (organizationOptional.isEmpty()) {
            throw new NoSuchElementException("Organization not found");
        }
        organizationRepository.deleteById(id);
        return true;
    }


}
