/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.service;

import com.bisa.demo.commons.ListClientMapper;
import com.bisa.demo.dto.ListClientResponse;
import com.bisa.demo.entity.Client;
import com.bisa.demo.enums.Accessibility;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.repository.IReferenceRepository;
import com.bisa.demo.validator.ListClientValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bisa.demo.repository.IListClientRepository;

/**
 *
 * @author alepaco.com
 */
@Log4j2
@AllArgsConstructor
@Service
@Component
public class ListClientService {

    @Autowired
    MultiLanguageMessagesService mlms;

    @Autowired
    ListClientValidator listClientValidator;

    @Autowired
    IListClientRepository repository;

    @Autowired
    IReferenceRepository referenceRepository;

    @Transactional
    public List<ListClientResponse> list(String accessibility) throws ExceptionResponse {
        ErrorCode errorCode = listClientValidator.validate(accessibility);

        if (!errorCode.isSuccessfull()) {
            throw new ExceptionResponse(errorCode.getCode(),
                    mlms.getMessage(errorCode.getCode()));
        }

        List<ListClientResponse> response = new ArrayList<>();

        switch (Accessibility.valueOf(accessibility.toUpperCase())) {
            case BUENA -> {
                response = accessibilityGood();
            }
            case REGULAR -> {
                response = accessibilityRegular();
            }
            case MALA -> {
                response = accessibilityBad();

            }
            case NULA -> {
                response = accessibilityNull();

            } 
        }

        return response;
    }

    private List<ListClientResponse> accessibilityGood() {
        Set<Client> combinedSet = new HashSet<>();
        combinedSet.addAll(repository.
                findAllByAccessibilityGood(2, 2));
        combinedSet.addAll(repository.
                findAllByAccessibilityGood(3, 1));

        List<Client> combinedList = new ArrayList<>(combinedSet);

        return ListClientMapper.mapperToDto(
                combinedList,
                referenceRepository);
    }

    private List<ListClientResponse> accessibilityRegular() {
        Set<Client> combinedSet = new HashSet<>();
        combinedSet.addAll(repository.
                findAllByAccessibilityRegularOption1(2));
        combinedSet.addAll(repository.
                findAllByAccessibilityRegularOption2(1, 1));

        List<Client> combinedList = new ArrayList<>(combinedSet);

        return ListClientMapper.mapperToDto(
                combinedList,
                referenceRepository);
    }

    private List<ListClientResponse> accessibilityBad() {
        return ListClientMapper.mapperToDto(
                repository.findAllByAccessibilityRegularBad(1),
                referenceRepository);
    }

    private List<ListClientResponse> accessibilityNull() {
        return ListClientMapper.mapperToDto(
                repository.findAllByAccessibilityRegularNull(),
                referenceRepository);
    }

}
