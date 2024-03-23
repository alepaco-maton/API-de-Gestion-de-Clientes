/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.service;

import com.bisa.demo.commons.CreateClientMapper;
import com.bisa.demo.dto.CreateClientRequest;
import com.bisa.demo.dto.CreateClientResponse;
import com.bisa.demo.dto.ListClientResponse;
import com.bisa.demo.entity.Client;
import com.bisa.demo.enums.Accessibility;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.repository.IClientRepository;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.validator.CreateClientValidator;
import com.bisa.demo.validator.ListClientValidator;
import java.util.List;
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
@Service
@Component
public class ClientService {

    @Autowired
    MultiLanguageMessagesService mlms;

    @Autowired
    CreateClientValidator createClientValidator;

    @Autowired
    ListClientValidator listClientValidator;

    @Autowired
    IClientRepository repository;

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

        return CreateClientMapper.mapperToDto(model);
    }

    public List<ListClientResponse> list(String accessibility) throws ExceptionResponse {
        ErrorCode errorCode = listClientValidator.validate(accessibility);

        if (!errorCode.isSuccessfull()) {
            throw new ExceptionResponse(errorCode.getCode(),
                    mlms.getMessage(errorCode.getCode()));
        }

        List<ListClientResponse> response;

//        switch (Accessibility.valueOf(accessibility.toUpperCase())) {
//            case BUENA:
//                response = repository.findAllByAccessibilityGood(2, 2).
//                        stream().map(cl -> new ListClientResponse(cl.getPerson().getId(), null, null, accessibility, dateOfBirth, address, accessibility, Integer.SIZE, accessibility, accessibility, accessibility, accessibility, references));
//                break;
//            case REGULAR:
//                response = ...; // Código para accesibilidad REGULAR
//                break;
//            case MALA:
//                response = ...; // Código para accesibilidad MALA
//                break;
//            case NULA:
//                response = ...; // Código para accesibilidad NULA
//                break;
//            default:
//                break;
//        }
//
//        Client model = repository.save(
//                CreateClientMapper.mapperToEntity(
//                        request, personRepository));

        return null;
    }

}
