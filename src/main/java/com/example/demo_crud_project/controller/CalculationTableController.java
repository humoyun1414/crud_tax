package com.example.demo_crud_project.controller;

import com.example.demo_crud_project.model.CalculateDto;
import com.example.demo_crud_project.model.CalculateDtoCondition2;
import com.example.demo_crud_project.model.CalculateDtoCondition3;
import com.example.demo_crud_project.model.request.CalculationTableRequest;
import com.example.demo_crud_project.service.CalculationTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.example.demo_crud_project.util.Constants.DEFAULT_VALUE_PAGE;
import static com.example.demo_crud_project.util.Constants.DEFAULT_VALUE_SIZE;

@RestController
@RequestMapping("/calculationTable")
public class CalculationTableController {
    @Autowired
    private CalculationTableService calculationTableService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CalculationTableRequest request) {
        return ResponseEntity.ok(calculationTableService.create(request));
    }

    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = DEFAULT_VALUE_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_VALUE_SIZE) int size) {
        return ResponseEntity.ok(calculationTableService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(calculationTableService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CalculationTableRequest request) {
        return ResponseEntity.ok(calculationTableService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(calculationTableService.delete(id));
    }

    @GetMapping("/calculate1/{month}/{limit}")
    public ResponseEntity<List<CalculateDto>> calculateRates(@PathVariable(value = "month")
                                                             String month,
                                                             @PathVariable(value = "limit")
                                                             String limit) {
        return ResponseEntity.ok(calculationTableService.calcRate(month, limit));
    }

    @GetMapping("/calculate2/{month}")
    public ResponseEntity<List<CalculateDtoCondition2>> calculateCondition2(@PathVariable(value = "month")
                                                                            String month) {
        return ResponseEntity.ok(calculationTableService.calcCondition2(month));
    }

    @GetMapping("/calculate3/{orgId}/{month}")
    public ResponseEntity<List<CalculateDtoCondition3>> calculateCondition3(@PathVariable(value = "orgId")
                                                                            int orgId,
                                                                            @PathVariable(value = "month")
                                                                            String month) {
        return ResponseEntity.ok(calculationTableService.calcCondition3(orgId, month));
    }
}
