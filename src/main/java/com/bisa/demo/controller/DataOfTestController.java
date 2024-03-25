/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.controller;

import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.service.TestUseCasesService;
import com.bisa.demo.service.CreateClientService;
import com.bisa.demo.service.ListClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URISyntaxException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alepaco.com
 */
@Log4j2
@RestController
@Tag(name = "Datos de pruebas API", description = "APIs para cargar datos de prueba.")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RequestMapping("/test")
public class DataOfTestController {

    @Autowired
    TestUseCasesService testUseCasesService;

    @Autowired
    CreateClientService createClientService;

    @Autowired
    ListClientService listClientService;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Pruebas basicas",
            description = "Este metodo nos permite cargar datos de prueba basicas. ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok - Solciitud procesada")
    })
    @GetMapping(produces = "application/json; charset=UTF-8")
    public ResponseEntity basicTest() throws URISyntaxException, ExceptionResponse {

        testUseCasesService.fillBasic();

        return ResponseEntity.ok(testUseCasesService.info());
    }

}
