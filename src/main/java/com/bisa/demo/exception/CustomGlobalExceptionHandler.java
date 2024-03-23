/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.exception;

import java.net.ConnectException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author alepaco.com
 */
@Log4j2
@Component
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
  
    @ExceptionHandler({ExceptionResponse.class})
    public ResponseEntity<Object> mihandleAll(Exception ex, WebRequest request) {
        ResponseEntity<Object> out = new ResponseEntity<>(ex.getMessage(), 
                HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST);

        StringBuilder sb = new StringBuilder();
        sb.append("-------------------RESPONSE----------------------\n").
                append("DATA ").append(out).append(", \n").
                append("------------------------------------------------\n");

        log.info(sb.toString());

        return out;
    }
    
    @ExceptionHandler({Exception.class, ConnectException.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ResponseEntity<Object> out = new ResponseEntity<>(ex.getMessage(), 
                HttpHeaders.EMPTY, HttpStatus.UNPROCESSABLE_ENTITY);

        StringBuilder sb = new StringBuilder();
        sb.append("-------------------RESPONSE----------------------\n").
                append("DATA ").append(out).append(", \n").
                append("------------------------------------------------\n");

        log.info(sb.toString());

        return out;
    }

}
