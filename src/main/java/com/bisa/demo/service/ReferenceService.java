/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.service;

import com.bisa.demo.commons.AddPersonalReferenceToClientMapper;
import com.bisa.demo.commons.RemovePersonalReferenceFromClientMapper;
import com.bisa.demo.dto.AddPersonalReferenceToClientRequest;
import com.bisa.demo.dto.AddPersonalReferenceToClientResponse;
import com.bisa.demo.dto.RemovePersonalReferenceFromClientRequest;
import com.bisa.demo.dto.RemovePersonalReferenceFromClientResponse;
import com.bisa.demo.entity.Client;
import com.bisa.demo.entity.Reference;
import com.bisa.demo.enums.ClientStatus;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.repository.IReferenceRepository;
import com.bisa.demo.validator.AddReferenceValidator;
import com.bisa.demo.validator.RemoveReferenceValidator;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bisa.demo.repository.ICreateClientRepository;

/**
 *
 * @author alepaco.com
 */
@Log4j2
@Service
@Component
public class ReferenceService {

    @Autowired
    MultiLanguageMessagesService mlms;

    @Autowired
    AddReferenceValidator addValidator;

    @Autowired
    RemoveReferenceValidator removeValidator;

    @Autowired
    IReferenceRepository repository;

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    ICreateClientRepository clientRepository;

    @Transactional
    public AddPersonalReferenceToClientResponse add(AddPersonalReferenceToClientRequest request) throws ExceptionResponse {
        ErrorCode errorCode = addValidator.validate(request);

        if (!errorCode.isSuccessfull()) {
            throw new ExceptionResponse(errorCode.getCode(),
                    mlms.getMessage(errorCode.getCode()));
        }

        Reference model = repository.save(
                AddPersonalReferenceToClientMapper.mapperToEntity(
                        request, clientRepository, personRepository));

        Client client = clientRepository.findById(request.getClientId()).get();

        if (!client.getStatus().equals(ClientStatus.ACTIVE)) {
            client.setStatus(ClientStatus.ACTIVE);
        }

        return AddPersonalReferenceToClientMapper.mapperToDto(model);
    }

    @Transactional
    public RemovePersonalReferenceFromClientResponse delete(
            RemovePersonalReferenceFromClientRequest request) throws ExceptionResponse {
        ErrorCode errorCode = removeValidator.validate(request);

        if (!errorCode.isSuccessfull()) {
            throw new ExceptionResponse(errorCode.getCode(),
                    mlms.getMessage(errorCode.getCode()));
        }

        Reference model = repository.findById(request.getId()).get();
        model.setReasonForElimination(request.getReasonForElimination());
        repository.saveAndFlush(model);
        
        List<Reference> references = repository.findAllByClientIdIdAndReasonForEliminationIsNull(
                model.getClientId().getId());
        
        if (references.isEmpty()) {
            Client client = clientRepository.findById(model.getClientId().getId()).get();
            client.setStatus(ClientStatus.LOCKED);
        }
        
        return RemovePersonalReferenceFromClientMapper.mapperToDto(model);
    }

}
