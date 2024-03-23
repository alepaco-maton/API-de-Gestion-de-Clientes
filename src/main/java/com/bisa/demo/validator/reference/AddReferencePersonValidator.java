/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator.reference;

import com.bisa.demo.dto.AddPersonalReferenceToClientRequest;
import com.bisa.demo.entity.Person;
import com.bisa.demo.entity.Reference;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.repository.IReferenceRepository;
import com.bisa.demo.validator.IValidator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author alepaco.com
 */
public class AddReferencePersonValidator implements IValidator<AddPersonalReferenceToClientRequest> {

    IPersonRepository personRepository;
    IReferenceRepository referenceRepository;

    public AddReferencePersonValidator(IPersonRepository personRepository,
            IReferenceRepository referenceRepository) {
        this.personRepository = personRepository;
        this.referenceRepository = referenceRepository;
    }

    @Override
    public ErrorCode validate(AddPersonalReferenceToClientRequest request) {
        if (request.getPersonId() == null) {
            return ErrorCode.ADD_PERSONAL_REFERENCE_TO_CLIENT_PERSON_ID_IS_REQUIRED;
        }

        Optional<Person> optional = personRepository.findById(request.getPersonId());

        if (!optional.isPresent()) {
            return ErrorCode.ADD_PERSONAL_REFERENCE_TO_CLIENT_PERSON_ID_IS_INVALID;
        }

        List<Reference> reference = referenceRepository.
                findAllByClientIdIdAndPersonIdIdAndReasonForEliminationIsNull(request.getClientId(), request.getPersonId());

        if (!reference.isEmpty()) {
            return ErrorCode.ADD_PERSONAL_REFERENCE_TO_CLIENT_EXISTING_REFERENCE;
        }

        return ErrorCode.SUCCESSFUL;
    }

}
