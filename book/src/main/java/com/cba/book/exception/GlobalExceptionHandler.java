package com.cba.book.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomBookCatalogueException.class)
    public ResponseEntity<ErrorResponse> handleException(CustomBookCatalogueException customEx){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(Collections.singletonList(customEx.getMessage()));
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoSuchBookExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(NoSuchBookExistsException customEx){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(Collections.singletonList(customEx.getMessage()));
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(Collections.singletonList(ex.getMessage()));
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(Integer.parseInt(String.valueOf(HttpStatus.BAD_REQUEST.value())));
        List list = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        errorResponse.setMessage(list);
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<Object>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
