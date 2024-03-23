/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.service;

import com.bisa.demo.commons.CreatePersonMapper;
import com.bisa.demo.dto.CreatePersonRequest;
import com.bisa.demo.dto.CreatePersonResponse;
import com.bisa.demo.entity.Person;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.validator.CreatePersonValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bisa.demo.validator.ICreatePersonValidator;

/**
 *
 * @author alepaco.com
 */
@Log4j2
@Service
@Component
public class PersonService {

    @Autowired
    MultiLanguageMessagesService mlms;

    @Autowired
    CreatePersonValidator validator;

    @Autowired
    IPersonRepository repository;
    
    @Transactional
    public CreatePersonResponse create(CreatePersonRequest request) throws ExceptionResponse {
        ErrorCode errorCode = validator.validate(request);

        if (!errorCode.isSuccessfull()) {
            throw new ExceptionResponse(errorCode.getCode(),
                    mlms.getMessage(errorCode.getCode()));
        }

        Person model = repository.save(CreatePersonMapper.mapperToEntity(request));

        return CreatePersonMapper.mapperToDto(model);
    }
    
}
