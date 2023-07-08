package com.example.demo_crud_project.controller;

import com.example.demo_crud_project.model.CalculateDto;
import com.example.demo_crud_project.model.CalculateDtoCondition2;
import com.example.demo_crud_project.model.CalculateDtoCondition3;
import com.example.demo_crud_project.model.CalculationTableDto;
import com.example.demo_crud_project.model.request.CalculationTableRequest;
import com.example.demo_crud_project.service.CalculationTableService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    @ApiOperation(value = "Create Calculation object", response = CalculationTableDto.class)
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CalculationTableRequest request) {
        return ResponseEntity.ok(calculationTableService.create(request));
    }

    @ApiOperation(value = "Get all calculation table information", response = Pageable.class)
    @GetMapping()
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = DEFAULT_VALUE_PAGE) int page,
                                     @RequestParam(value = "size", defaultValue = DEFAULT_VALUE_SIZE) int size) {
        return ResponseEntity.ok(calculationTableService.findAll(page, size));
    }

    @ApiOperation(value = "Get one calculation row by id", response = CalculationTableDto.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(calculationTableService.findById(id));
    }

    @ApiOperation(value = "Update calculation info by id ", response = CalculationTableDto.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CalculationTableRequest request) {
        return ResponseEntity.ok(calculationTableService.update(id, request));
    }

    @ApiOperation(value = "Delete calculation one row by id", response = Boolean.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(calculationTableService.delete(id));
    }

    @ApiOperation(value = "Get calculation info by month and limit rate ", response = CalculateDto.class)
    @GetMapping("/calculate1/{month}/{limit}")
    public ResponseEntity<List<CalculateDto>> calculateRates(@PathVariable(value = "month")
                                                             String month,
                                                             @PathVariable(value = "limit")
                                                             String limit) {
        return ResponseEntity.ok(calculationTableService.calcRate(month, limit));
    }

    @ApiOperation(value = "Get calculation info by month where different region one pinfl employee ", response = CalculateDtoCondition2.class)
    @GetMapping("/calculate2/{month}")
    public ResponseEntity<List<CalculateDtoCondition2>> calculateCondition2(@PathVariable(value = "month")
                                                                            String month) {
        return ResponseEntity.ok(calculationTableService.calcCondition2(month));
    }

    @ApiOperation(value = "Get calculation info by month and organization id where organization id full info ", response = CalculateDtoCondition3.class)
    @GetMapping("/calculate3/{orgId}/{month}")
    public ResponseEntity<List<CalculateDtoCondition3>> calculateCondition3(@PathVariable(value = "orgId")
                                                                            int orgId,
                                                                            @PathVariable(value = "month")
                                                                            String month) {
        return ResponseEntity.ok(calculationTableService.calcCondition3(orgId, month));
    }
}
