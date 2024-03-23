/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator.reference;

import com.bisa.demo.commons.AppTools;
import com.bisa.demo.dto.RemovePersonalReferenceFromClientRequest;
import com.bisa.demo.entity.Reference;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.repository.IReferenceRepository;
import com.bisa.demo.validator.IValidator;
import java.util.Optional;

/**
 *
 * @author alepaco.com
 */
public class RemoveReferenceReferenceIdValidator implements IValidator<RemovePersonalReferenceFromClientRequest> {

    IReferenceRepository referenceRepository;

    public RemoveReferenceReferenceIdValidator(IReferenceRepository referenceRepository) {
        this.referenceRepository = referenceRepository;
    }

    @Override
    public ErrorCode validate(RemovePersonalReferenceFromClientRequest request) {
        if (request.getId() == null) {
            return ErrorCode.REMOVE_PERSONAL_REFERENCE_TO_CLIENT_REFERENCE_ID_IS_REQUIRED;
        }

        Optional<Reference> optional = referenceRepository.findById(request.getId());

        if (!optional.isPresent()) {
            return ErrorCode.REMOVE_PERSONAL_REFERENCE_TO_CLIENT_REFERENCE_ID_IS_INVALID;
        }
        
        Reference reference = optional.get();
        
        if (!AppTools.isBlank(reference.getReasonForElimination())) {
            return ErrorCode.REMOVE_PERSONAL_REFERENCE_TO_CLIENT_REFERENCE_IS_ALREADY_REMOVED;
        }

        return ErrorCode.SUCCESSFUL;
    }

}
