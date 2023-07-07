package com.example.demo_crud_project.controller;

import com.example.demo_crud_project.model.request.OrganizationRequest;
import com.example.demo_crud_project.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.demo_crud_project.util.Constants.DEFAULT_VALUE_PAGE;
import static com.example.demo_crud_project.util.Constants.DEFAULT_VALUE_SIZE;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.create(request));
    }

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = DEFAULT_VALUE_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_VALUE_SIZE) int size) {
        return ResponseEntity.ok(organizationService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(organizationService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(organizationService.delete(id));
    }
}
