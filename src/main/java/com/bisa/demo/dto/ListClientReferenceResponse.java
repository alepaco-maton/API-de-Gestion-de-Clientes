/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author alepaco.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListClientReferenceResponse {

    private Integer id;
    private Integer personId;
    private String personFullName;
    private boolean isClient;
    private boolean referenceRemoved;
    private String reasonForElimination;

}
