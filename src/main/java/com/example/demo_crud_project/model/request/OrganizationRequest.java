package com.example.demo_crud_project.model.request;

import java.util.UUID;

public record OrganizationRequest(String name,
                                  Integer regionId,
                                  Integer parent) {
}
