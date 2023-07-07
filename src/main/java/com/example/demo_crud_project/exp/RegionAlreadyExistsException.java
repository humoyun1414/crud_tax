package com.example.demo_crud_project.exp;

public class RegionAlreadyExistsException extends RuntimeException{
    public RegionAlreadyExistsException(String message) {
        super(message);
    }
}
