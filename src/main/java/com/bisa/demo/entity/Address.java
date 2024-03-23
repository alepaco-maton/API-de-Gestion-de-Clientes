/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.entity;

import jakarta.persistence.Entity;
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
//@Entity
public class Address {
    
    private String geographicLocation;
    private String areaNeighborhoodStreet;
    private String addressNumber;
    private String reference;
    
}
