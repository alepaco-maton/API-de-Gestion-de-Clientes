/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.controller;

import com.bisa.demo.dto.CreateClientRequest;
import com.bisa.demo.dto.CreateClientResponse;
import com.bisa.demo.dto.ListClientResponse;
import com.bisa.demo.exception.AppDemoException;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.service.CreateClientService;
import com.bisa.demo.service.ListClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Tag(name = "Clientes API", description = "APIs para listar y crear clientes.")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RequestMapping("/client")
public class ClientController {

    @Autowired
    CreateClientService createClientService;

    @Autowired
    ListClientService listClientService;

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear cliente",
            description = "Este metodo nos permite crear clientes.")
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
    public ResponseEntity<CreateClientResponse> create(@RequestBody CreateClientRequest request) throws URISyntaxException, ExceptionResponse {
        CreateClientResponse response = createClientService.create(request);

        return ResponseEntity.created(new URI("/" + response.getId())).body(response);
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar clientes",
            description = "Este metodo nos permite listar los clientes filtrados por accesibilidad [BUENA, REGULAR, MALA, NULA].")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok - Solciitud procesada"),
        @ApiResponse(responseCode = "400", description = "Bad Request - Mesaje de validaciones de reglas de negocio.", 
                content = { @Content(mediaType = "application/json", 
                        schema = @Schema(implementation = AppDemoException.class))
                        }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error - Error al procesar la solicitud.", 
                content = { @Content(mediaType = "application/json", schema = 
            @Schema(implementation = AppDemoException.class)) }) 
    })
    @GetMapping(produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<ListClientResponse>> list(@RequestParam
            @Parameter(name = "accessibility", description = "Filtro por accesibilidad, posibles valores [BUENA, REGULAR, MALA, NULA]",
                    example = "BUENA") String accessibility) throws URISyntaxException, ExceptionResponse {
        List<ListClientResponse> response = listClientService.list(accessibility);

        return ResponseEntity.ok().body(response);
    }

}
