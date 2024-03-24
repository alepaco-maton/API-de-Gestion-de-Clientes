/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator;

import com.bisa.demo.commons.AppTools;
import com.bisa.demo.enums.Accessibility;
import com.bisa.demo.exception.ErrorCode;
import org.springframework.stereotype.Component;

/**
 *
 * @author alepaco.com
 */
@Component
public class ListClientValidator {
   
    public ErrorCode validate(String accessibility) {
        if (AppTools.isBlank(accessibility)) {
            return ErrorCode.LIST_CLIENTS_BY_ACCESSIBILITY_FILTER_ACCESSIBILITY_IS_REQUIRED;
        }

        if (!Accessibility.isValid(accessibility)) {
            return ErrorCode.LIST_CLIENTS_BY_ACCESSIBILITY_FILTER_ACCESSIBILITY_IS_INVALID;
        }

        return ErrorCode.SUCCESSFUL;
    }
}
