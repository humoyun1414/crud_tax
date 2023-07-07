package com.example.demo_crud_project.controller;

import com.example.demo_crud_project.model.request.RegionRequest;
import com.example.demo_crud_project.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo_crud_project.util.Constants.DEFAULT_VALUE_PAGE;
import static com.example.demo_crud_project.util.Constants.DEFAULT_VALUE_SIZE;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody RegionRequest request) {
        return ResponseEntity.ok(regionService.create(request));
    }

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = DEFAULT_VALUE_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_VALUE_SIZE) int size) {
        return ResponseEntity.ok(regionService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(regionService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody RegionRequest request) {
        return ResponseEntity.ok(regionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(regionService.delete(id));
    }
}
