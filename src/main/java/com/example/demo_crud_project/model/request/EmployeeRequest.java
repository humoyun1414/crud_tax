package com.example.demo_crud_project.model.request;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeRequest(String firstname,
                              String lastname,
                              String pinfl,
                              LocalDate hireDate,
                              Integer organizationId) {
}
