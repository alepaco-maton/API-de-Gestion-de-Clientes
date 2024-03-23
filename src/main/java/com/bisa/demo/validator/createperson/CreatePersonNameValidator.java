/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator.createperson;

import com.bisa.demo.validator.*;
import com.bisa.demo.commons.AppTools;
import com.bisa.demo.dto.CreatePersonRequest;
import com.bisa.demo.exception.ErrorCode;

/**
 *
 * @author alepaco.com
 */
public class CreatePersonNameValidator implements ICreatePersonValidator {

    public static final int NAME_LENGTH_MIN = 1;
    public static final int NAME_LENGTH_MAX = 100;

    @Override
    public ErrorCode validate(CreatePersonRequest request) {
        if (AppTools.isBlank(request.getName())) {
            return ErrorCode.CREATE_PERSON_NAME_IS_REQUIRED;
        }

        if (!AppTools.isTheLengthOfTheStringValid(request.getName(),
                NAME_LENGTH_MIN, NAME_LENGTH_MAX)) {
            return ErrorCode.CREATE_PERSON_NAME_IS_INVALID;
        }

        return ErrorCode.SUCCESSFUL;
    } 

}
