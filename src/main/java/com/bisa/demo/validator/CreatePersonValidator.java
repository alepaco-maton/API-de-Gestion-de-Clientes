/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator;

import com.bisa.demo.dto.CreatePersonRequest;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.validator.createperson.CreatePersonAddressValidator;
import com.bisa.demo.validator.createperson.CreatePersonDateOfBirthValidator;
import com.bisa.demo.validator.createperson.CreatePersonIdentityCardValidator;
import com.bisa.demo.validator.createperson.CreatePersonMaterrnalLastNameValidator;
import com.bisa.demo.validator.createperson.CreatePersonNameValidator;
import com.bisa.demo.validator.createperson.CreatePersonPaterrnalLastNameValidator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CreatePersonValidator {
    
    public ErrorCode validate(CreatePersonRequest request) {
        List<ICreatePersonValidator> validators = new ArrayList<>();
        validators.add(new CreatePersonNameValidator());
        validators.add(new CreatePersonPaterrnalLastNameValidator());
        validators.add(new CreatePersonMaterrnalLastNameValidator());
        validators.add(new CreatePersonDateOfBirthValidator());
        validators.add(new CreatePersonAddressValidator());
        validators.add(new CreatePersonIdentityCardValidator());

        for (ICreatePersonValidator val : validators) {
            ErrorCode errorCode = val.validate(request);
            
            if (!errorCode.isSuccessfull()) {
                return errorCode;
            }
        }
        
        return ErrorCode.SUCCESSFUL;
    }

}
