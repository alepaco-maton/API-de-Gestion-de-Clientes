/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator.createperson;

import com.bisa.demo.validator.*;
import com.bisa.demo.dto.CreatePersonRequest;
import com.bisa.demo.exception.ErrorCode;
import java.util.Date;

/**
 *
 * @author alepaco.com
 */
public class CreatePersonDateOfBirthValidator implements IValidator<CreatePersonRequest> {

    public static final long DATE_OF_BIRTH_MIN = 0L;

    @Override
    public ErrorCode validate(CreatePersonRequest request) {
        if (request.getDateOfBirth() == null) {
            return ErrorCode.CREATE_PERSON_DATE_OF_BIRTH_IS_REQUIRED;
        }

        if (DATE_OF_BIRTH_MIN > request.getDateOfBirth().getTime()
                || request.getDateOfBirth().getTime() > (new Date()).getTime()) {
            return ErrorCode.CREATE_PERSON_DATE_OF_BIRTH_IS_INVALID;
        }

        return ErrorCode.SUCCESSFUL;
    }

}
