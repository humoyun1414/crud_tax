package com.example.demo_crud_project.controller;

import com.example.demo_crud_project.model.request.EmployeeRequest;
import com.example.demo_crud_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.demo_crud_project.util.Constants.DEFAULT_VALUE_PAGE;
import static com.example.demo_crud_project.util.Constants.DEFAULT_VALUE_SIZE;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.create(request));
    }

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = DEFAULT_VALUE_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_VALUE_SIZE) int size) {
        return ResponseEntity.ok(employeeService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }
}
