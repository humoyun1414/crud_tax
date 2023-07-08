package com.example.demo_crud_project.controller;

import com.example.demo_crud_project.exp.ItemAlreadyExistsException;
import com.example.demo_crud_project.exp.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ItemAlreadyExistsException.class})
    public ResponseEntity<Object> handleBadRequestException(ItemAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse() {
            @Override
            public HttpStatusCode getStatusCode() {
                return HttpStatusCode.valueOf(400);
            }

            @Override
            public ProblemDetail getBody() {
                return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
            }
        };

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(ItemNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse() {
            @Override
            public HttpStatusCode getStatusCode() {
                return HttpStatusCode.valueOf(404);
            }

            @Override
            public ProblemDetail getBody() {
                return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
            }
        };
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
