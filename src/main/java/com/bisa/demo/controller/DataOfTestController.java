/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.controller;

import com.bisa.demo.dto.ListClientResponse;
import com.bisa.demo.exception.AppDemoException;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.service.TestUseCasesService;
import com.bisa.demo.service.CreateClientService;
import com.bisa.demo.service.ListClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URISyntaxException;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity basicTest(@RequestParam
            @Parameter(name = "accessibility", description = "Filtro por accesibilidad, posibles valores [BUENA, REGULAR, MALA, NULA]",
                    example = "BUENA") String accessibility) throws URISyntaxException, ExceptionResponse {

        testUseCasesService.fillBasic();

        return ResponseEntity.ok(""" 
                                 * El identificador de cliente y persona son autoincrementables
                                 
                                 Personas NO clientes PersonaID[3, 6 y 9] las demas son personas son
                                 clientes PersonaID[1,2,4,5,7,8,10,11,12,13]
                                 Casos de prueba: 
                                 cliente 1 => 2 referncias que no son clientes, PersonasID(2 y 4) 
                                 cliente 2 => 2 referncias que son clientes PersonasID(3 y 6) 
                                 cliente 3 => 2 referncias, un cliente y otro no cliente PersonasID(1 y 6) 
                                 cliente 4 => 3 referncias, 1 cliente y 2 no clientes PersonasID(1, 2, y 3) 
                                 cliente 5 => 4 referncias, 2 clientes y 2 no cliente PersonasID(1, 2, 3 y 6) 
                                 cliente 6 => 1 referncias 1 cliente PersonasID(9) 
                                 cliente 7 => 3 referncias que son clientes PersonasID(3, 6 y 9) 
                                 cliente 8 => 3 referncias 1 no cliente y 2 clientes PersonasID(1, 6 y 9) 
                                 cliente 9 => 1 referncias 1 no cliente PersonasID(1) 
                                 cliente 10 => 0 referncias 
                                 
                                 Accesibilidad = BUENA
                                 Regla 1 = TOTAL REFERENCIAS (>= 2) y REFERENCIAS DE TIPO CLIENTE (>=2)
                                 Regla 2 = TOTAL REFERENCIAS (>= 3) y REFERENCIAS DE TIPO CLIENTE (>=1)
                                 
                                 Los clientes a retornar con ID 
                                 [
                                 cliente ID = 2 (regla 1) PersonasID(3 y 6) , 
                                 cliente ID = 4 (regla 1 y 2) PersonasID(1, 2, y 3) , 
                                 cliente ID = 5 (regla 1 y 2) PersonasID(1, 2, 3 y 6),
                                 cliente ID = 7 (regla 1 y 2) PersonasID(3, 6 y 9),
                                 cliente ID = 8 (regla 1 y 2) PersonasID(1, 6 y 9) ]
                                  
                                 Accesibilidad = REGULAR
                                 Regla 1 = TOTAL REFERENCIAS (>= 2) y REFERENCIAS DE TIPO CLIENTE (0)
                                 Regla 2 = TOTAL REFERENCIAS (1) y REFERENCIAS DE TIPO CLIENTE (1)
                                 Los clientes a retornar con ID 
                                 [
                                 cliente ID = 1 (regla 1) PersonasID(2 y 4) , 
                                 cliente ID = 6 (regla 2) PersonasID(9) , 
                                 ]
                                 
                                 Accesibilidad = MALA
                                 Regla 1 = TOTAL REFERENCIAS (1) y REFERENCIAS DE TIPO CLIENTE (0)
                                 Los clientes a retornar con ID 
                                 [
                                 cliente ID = 9 (regla 1) PersonasID(1)
                                 ]
                                 
                                 Accesibilidad = MALA
                                 Regla 1 = TOTAL REFERENCIAS (0) y REFERENCIAS DE TIPO CLIENTE (0)
                                 Los clientes a retornar con ID 
                                 [
                                 cliente ID = 10 (regla 1) 0 referncias
                                 ]
                                 """);
    }

}
