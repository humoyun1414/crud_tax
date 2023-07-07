package com.example.demo_crud_project.service;

import com.example.demo_crud_project.domain.Employee;
import com.example.demo_crud_project.exp.EmployeeAlreadyExistsException;
import com.example.demo_crud_project.model.EmployeeDto;
import com.example.demo_crud_project.model.request.EmployeeRequest;
import com.example.demo_crud_project.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    public EmployeeDto create(EmployeeRequest request) {

        Employee employee = objectMapper.convertValue(request, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return objectMapper.convertValue(savedEmployee, EmployeeDto.class);
    }

    public PageImpl<EmployeeDto> findAll(int page, int size) {
        List<EmployeeDto> dtoList = new LinkedList<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        employeePage.forEach(employee -> dtoList.add(objectMapper.convertValue(employee, EmployeeDto.class)));
        return new PageImpl<>(dtoList, pageable, employeePage.getTotalPages());

    }

    public EmployeeDto findById(Integer id) {
        return employeeRepository.findById(id)
                .map(employee -> objectMapper.convertValue(employee, EmployeeDto.class)).orElseThrow();
    }

    public Optional<EmployeeDto> update(Integer id, EmployeeRequest request) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty())
            throw new NoSuchElementException("Employee not found");

        return employeeOptional
                .map(employee -> {
                    employee.setFirstname(request.firstname());
                    employee.setPinfl(request.pinfl());
                    employee.setLastname(request.lastname());
                    employee.setHireDate(request.hireDate());
                    employee.setOrganizationId(request.organizationId());
                    Employee savedEmployee = employeeRepository.save(employee);
                    return objectMapper.convertValue(savedEmployee, EmployeeDto.class);
                });

    }

    public Boolean delete(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new NoSuchElementException("Employee not found");
        }
        employeeRepository.deleteById(id);
        return true;
    }


}
