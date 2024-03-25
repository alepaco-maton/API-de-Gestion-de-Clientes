/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator.reference;

import com.bisa.demo.dto.AddPersonalReferenceToClientRequest;
import com.bisa.demo.entity.Client;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.validator.IValidator;
import java.util.Optional;
import com.bisa.demo.repository.ICreateClientRepository;

/**
 *
 * @author alepaco.com
 */
public class AddReferenceClientValidator implements IValidator<AddPersonalReferenceToClientRequest> {

    ICreateClientRepository clientRepository;

    public AddReferenceClientValidator(ICreateClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ErrorCode validate(AddPersonalReferenceToClientRequest request) {
        if (request.getPersonId() == null) {
            return ErrorCode.ADD_PERSONAL_REFERENCE_TO_CLIENT_CLIENT_ID_IS_REQUIRED;
        }

        Optional<Client> optional = clientRepository.findById(request.getClientId());

        if (!optional.isPresent()) {
            return ErrorCode.ADD_PERSONAL_REFERENCE_TO_CLIENT_CLIENT_ID_IS_INVALID;
        }

        if (optional.get().getPerson().getId().equals(request.getPersonId())) {
            return ErrorCode.ADD_PERSONAL_REFERENCE_TO_CLIENT_CLIENT_ID_IS_INVALID_BY_THEY_ARE_THE_SAME_PERSON;
        }

        return ErrorCode.SUCCESSFUL;
    }

}
