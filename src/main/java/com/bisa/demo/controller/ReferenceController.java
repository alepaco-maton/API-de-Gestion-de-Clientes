/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.controller;

import com.bisa.demo.dto.AddPersonalReferenceToClientRequest;
import com.bisa.demo.dto.AddPersonalReferenceToClientResponse;
import com.bisa.demo.dto.CreatePersonRequest;
import com.bisa.demo.dto.CreatePersonResponse;
import com.bisa.demo.dto.RemovePersonalReferenceFromClientRequest;
import com.bisa.demo.dto.RemovePersonalReferenceFromClientResponse;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.service.PersonService;
import com.bisa.demo.service.ReferenceService;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alepaco.com
 */
@Log4j2
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/reference")
public class ReferenceController {
    
    @Autowired
    ReferenceService service;

    @PostMapping
    public ResponseEntity create(@RequestBody AddPersonalReferenceToClientRequest request) throws URISyntaxException, ExceptionResponse {
        AddPersonalReferenceToClientResponse response = service.add(request);

        return ResponseEntity.created(new URI("/" + response.getId())).body(response);
    }
        
    @DeleteMapping
    public ResponseEntity delete(@RequestBody RemovePersonalReferenceFromClientRequest request) throws ExceptionResponse {
        RemovePersonalReferenceFromClientResponse response = service.delete(request);

        return ResponseEntity.ok().body(response);
    }
    
}
