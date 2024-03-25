/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator;

import com.bisa.demo.dto.AddPersonalReferenceToClientRequest;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.repository.IReferenceRepository;
import com.bisa.demo.validator.reference.AddReferenceClientValidator;
import com.bisa.demo.validator.reference.AddReferencePersonValidator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bisa.demo.repository.ICreateClientRepository;

/**
 *
 * @author alepaco.com
 */
@Component
public class AddReferenceValidator {

    @Autowired
    ICreateClientRepository clientRepository;

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IReferenceRepository referenceRepository;

    public ErrorCode validate(AddPersonalReferenceToClientRequest request) {
        List<IValidator<AddPersonalReferenceToClientRequest>> validators = new ArrayList<>();
        validators.add(new AddReferenceClientValidator(clientRepository));
        validators.add(new AddReferencePersonValidator(personRepository, referenceRepository));

        for (IValidator val : validators) {
            ErrorCode errorCode = val.validate(request);

            if (!errorCode.isSuccessfull()) {
                return errorCode;
            }
        }

        return ErrorCode.SUCCESSFUL;
    }

}
