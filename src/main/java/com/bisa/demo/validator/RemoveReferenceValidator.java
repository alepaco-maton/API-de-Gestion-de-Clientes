/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator;

import com.bisa.demo.dto.RemovePersonalReferenceFromClientRequest;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.repository.IReferenceRepository;
import com.bisa.demo.validator.reference.RemoveReferenceReasonForEliminationValidator;
import com.bisa.demo.validator.reference.RemoveReferenceReferenceIdValidator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author alepaco.com
 */
@Component
public class RemoveReferenceValidator {

    @Autowired
    IReferenceRepository referenceRepository;

    public ErrorCode validate(RemovePersonalReferenceFromClientRequest request) {
        List<IValidator<RemovePersonalReferenceFromClientRequest>> validators = new ArrayList<>();
        validators.add(new RemoveReferenceReasonForEliminationValidator());
        validators.add(new RemoveReferenceReferenceIdValidator(referenceRepository));

        for (IValidator val : validators) {
            ErrorCode errorCode = val.validate(request);

            if (!errorCode.isSuccessfull()) {
                return errorCode;
            }
        }

        return ErrorCode.SUCCESSFUL;
    }

}
