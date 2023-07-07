package com.example.demo_crud_project.service;

import com.example.demo_crud_project.domain.CalculationTable;
import com.example.demo_crud_project.model.CalculationTableDto;
import com.example.demo_crud_project.model.request.CalculationTableRequest;
import com.example.demo_crud_project.repository.CalculationTableRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculationTableService {

    private final CalculationTableRepository calculationTableRepository;
    private final ObjectMapper objectMapper;

    public CalculationTableService(CalculationTableRepository calculationTableRepository, ObjectMapper objectMapper) {
        this.calculationTableRepository = calculationTableRepository;
        this.objectMapper = objectMapper;
    }

    public CalculationTableDto create(CalculationTableRequest request) {

        CalculationTable calculationTable = objectMapper.convertValue(request, CalculationTable.class);
        CalculationTable savedCalculationTable = calculationTableRepository.save(calculationTable);
        return objectMapper.convertValue(savedCalculationTable, CalculationTableDto.class);
    }

    public PageImpl<CalculationTableDto> findAll(int page, int size) {
        List<CalculationTableDto> dtoList = new LinkedList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<CalculationTable> calculationTablePage = calculationTableRepository.findAll(pageable);

        calculationTablePage.forEach(calculationTable -> dtoList.add(objectMapper.convertValue(calculationTable, CalculationTableDto.class)));
        return new PageImpl<>(dtoList, pageable, calculationTablePage.getTotalPages());

    }

    public CalculationTableDto findById(Integer id) {
        return calculationTableRepository.findById(id)
                .map(calculationTable -> objectMapper.convertValue(calculationTable, CalculationTableDto.class)).orElseThrow();
    }

    public Optional<CalculationTableDto> update(Integer id, CalculationTableRequest request) {
        Optional<CalculationTable> calculationTableOptional = calculationTableRepository.findById(id);
        if (calculationTableOptional.isEmpty())
            throw new NoSuchElementException("CalculationTable not found");

        return calculationTableOptional
                .map(calculationTable -> {
                    calculationTable.setCalculationType(request.calculationType());
                    calculationTable.setAmount(request.amount());
                    calculationTable.setRate(request.rate());
                    calculationTable.setEmployeeId(request.employeeId());
                    calculationTable.setOrganizationId(request.organizationId());

                    CalculationTable savedCalculationTable = calculationTableRepository.save(calculationTable);
                    return objectMapper.convertValue(savedCalculationTable, CalculationTableDto.class);
                });

    }

    public Boolean delete(Integer id) {
        Optional<CalculationTable> calculationTableOptional = calculationTableRepository.findById(id);
        if (calculationTableOptional.isEmpty()) {
            throw new NoSuchElementException("CalculationTable not found");
        }
        calculationTableRepository.deleteById(id);
        return true;
    }


}