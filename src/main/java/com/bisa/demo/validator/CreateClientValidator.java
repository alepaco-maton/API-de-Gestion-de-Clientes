/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator;

import com.bisa.demo.dto.CreateClientRequest;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.validator.createclient.CreateClientCannotBeLessThan20YearsOldValidator;
import com.bisa.demo.validator.createclient.CreateClientEmailValidator;
import com.bisa.demo.validator.createclient.CreateClientOccupationValidator;
import com.bisa.demo.validator.createclient.CreateClientTelephoneValidator;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bisa.demo.repository.ICreateClientRepository;

/**
 *
 * @author alepaco.com
 */
@AllArgsConstructor
@Component
public class CreateClientValidator {
    
    @Autowired
    IPersonRepository personRepository;
    
    @Autowired
    ICreateClientRepository clientRepository;
    
    public ErrorCode validate(CreateClientRequest request) {
        List<IValidator<CreateClientRequest>> validators = new ArrayList<>();
        validators.add(new CreateClientEmailValidator());
        validators.add(new CreateClientTelephoneValidator());
        validators.add(new CreateClientOccupationValidator());
        validators.add(new CreateClientCannotBeLessThan20YearsOldValidator(
                personRepository, clientRepository));

        for (IValidator val : validators) {
            ErrorCode errorCode = val.validate(request);

            if (!errorCode.isSuccessfull()) {
                return errorCode;
            }
        }

        return ErrorCode.SUCCESSFUL;
    }
}
