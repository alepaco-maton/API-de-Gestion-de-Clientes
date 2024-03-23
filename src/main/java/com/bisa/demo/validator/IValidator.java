/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bisa.demo.validator;

import com.bisa.demo.exception.ErrorCode;

/**
 *
 * @author alepaco.com
 */
public interface IValidator<T> {
    
    ErrorCode validate(T request);
    
}
