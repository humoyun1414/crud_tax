package com.example.demo_crud_project.model.request;

import com.example.demo_crud_project.util.CalculationTypes;


public record CalculationTableRequest(Integer employeeId,
                                      Double amount,
                                      int rate,
                                      Integer organizationId,
                                      CalculationTypes calculationType) {
}
