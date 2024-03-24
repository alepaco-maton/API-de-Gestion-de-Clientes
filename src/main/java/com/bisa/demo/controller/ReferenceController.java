/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.controller;

import com.bisa.demo.dto.AddPersonalReferenceToClientRequest;
import com.bisa.demo.dto.AddPersonalReferenceToClientResponse;
import com.bisa.demo.dto.RemovePersonalReferenceFromClientRequest;
import com.bisa.demo.dto.RemovePersonalReferenceFromClientResponse;
import com.bisa.demo.exception.AppDemoException;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.service.ReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Tag(name = "Referencias API", description = "APIs para añadir y elimninar referencias de los clientes.")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/reference")
public class ReferenceController {

    @Autowired
    ReferenceService service;

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Añadir referencia",
            description = "Este metodo nos permite añadir referencias a los clientes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created - Recurso creado"),
        @ApiResponse(responseCode = "400", description = "Bad Request - Mesaje de validaciones de reglas de negocio.", 
                content = { @Content(mediaType = "application/json", 
                        schema = @Schema(implementation = AppDemoException.class))
                        }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error - Error al procesar la solicitud.", 
                content = { @Content(mediaType = "application/json", schema = 
            @Schema(implementation = AppDemoException.class)) }) 
    })
    @PostMapping(consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public ResponseEntity<AddPersonalReferenceToClientResponse> create(@RequestBody AddPersonalReferenceToClientRequest request) throws URISyntaxException, ExceptionResponse {
        AddPersonalReferenceToClientResponse response = service.add(request);

        return ResponseEntity.created(new URI("/" + response.getId())).body(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Eliminar referencia",
            description = "Este metodo nos permite eliminar las referencias de los clientes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully - Respuesta Satisfactoria"),
        @ApiResponse(responseCode = "400", description = "Bad Request - Mesaje de validaciones de reglas de negocio.", 
                content = { @Content(mediaType = "application/json", 
                        schema = @Schema(implementation = AppDemoException.class))
                        }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error - Error al procesar la solicitud.", 
                content = { @Content(mediaType = "application/json", schema = 
            @Schema(implementation = AppDemoException.class)) }) 
    })
    @DeleteMapping(consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
    public ResponseEntity<RemovePersonalReferenceFromClientResponse> delete(@RequestBody RemovePersonalReferenceFromClientRequest request) throws ExceptionResponse {
        RemovePersonalReferenceFromClientResponse response = service.delete(request);

        return ResponseEntity.ok().body(response);
    }

}
