/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.service;

import com.bisa.demo.commons.CreateClientMapper;
import com.bisa.demo.dto.CreateClientRequest;
import com.bisa.demo.dto.CreateClientResponse;
import com.bisa.demo.entity.Client;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.repository.ICreateClientRepository;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.validator.CreateClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alepaco.com
 */
@Log4j2
@AllArgsConstructor
@Service
@Component
public class CreateClientService {

    @Autowired
    MultiLanguageMessagesService mlms;

    @Autowired
    CreateClientValidator createClientValidator;

    @Autowired 
    ICreateClientRepository repository;

    @Autowired
    IPersonRepository personRepository;
  
    @Transactional
    public CreateClientResponse create(CreateClientRequest request) throws ExceptionResponse {
        ErrorCode errorCode = createClientValidator.validate(request);

        if (!errorCode.isSuccessfull()) {
            throw new ExceptionResponse(errorCode.getCode(),
                    mlms.getMessage(errorCode.getCode()));
        }

        Client model = repository.save(
                CreateClientMapper.mapperToEntity(
                        request, personRepository));

        model.getPerson().setClient(true);

        return CreateClientMapper.mapperToDto(model);
    }
 
}
