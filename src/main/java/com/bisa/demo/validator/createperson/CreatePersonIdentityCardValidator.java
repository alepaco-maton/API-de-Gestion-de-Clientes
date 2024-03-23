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
public class CreatePersonIdentityCardValidator implements IValidator<CreatePersonRequest> {

    // TODO Se debe de revisar las normativas. Expresión regular para CI con 7 digitos
    public static final String IDENTITY_CARD_REGEX = "^[0-9]{7}$";

    @Override
    public ErrorCode validate(CreatePersonRequest request) {
        if (AppTools.isBlank(request.getIdentityCard())) {
            return ErrorCode.CREATE_PERSON_IDENTITY_CARD_IS_REQUIRED;
        }

        if (!request.getIdentityCard().matches(IDENTITY_CARD_REGEX)) {
            return ErrorCode.CREATE_PERSON_IDENTITY_CARD_IS_INVALID;
        }

        return ErrorCode.SUCCESSFUL;
    }

}
