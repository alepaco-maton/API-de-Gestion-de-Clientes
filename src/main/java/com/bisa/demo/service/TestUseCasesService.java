/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.service;

import com.bisa.demo.commons.TestUseCases;
import com.bisa.demo.repository.ICreateClientRepository;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.repository.IReferenceRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author alepaco.com
 */
@Log4j2
@Service
@Component
@Order(1)
@Scope("singleton")
public class TestUseCasesService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private ICreateClientRepository clientRepository;

    @Autowired
    private IReferenceRepository referenceRepository;
 
    
    TestUseCases test;
     
    @PostConstruct
    public void init() {
        test = new TestUseCases(personRepository, clientRepository, referenceRepository);
        fillBasic();
    }

    public void fillBasic() {
        test.fillBasic();
    }
    
}
