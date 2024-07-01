/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.exception;

import lombok.Getter;

/**
 *
 * @author alepaco.com
 */
@Getter
public class ExceptionResponse extends Exception {
    
    private final String code;
    private final String message;

    public ExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public ExceptionResponse(String code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }
    
}
