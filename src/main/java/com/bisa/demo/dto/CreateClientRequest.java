/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author alepaco.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest {

    @Schema(name = "Persona ID", description = "Identificador unico de persona.",
            nullable = false, example = "1")
    private Integer personId;
    @Schema(name = "Email", description = "Correo electronico.",
            nullable = false, example = "alepaco.maton@gmail.com")
    private String email;
    @Schema(name = "Telefono", description = "Numero de telefono.",
            nullable = false, example = "3698454")
    private String telephone;
    @Schema(name = "Ocupacion", description = "Ocupacion de la persona.",
            nullable = false, example = "Albañil, Pintor, Programador, etc.")
    private String occupation;

}
