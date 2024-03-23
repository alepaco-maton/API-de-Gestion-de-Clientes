/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.dto;

import com.bisa.demo.entity.Address;
import java.util.Date;
import java.util.List;
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
public class ListClientResponse {
 
    private Integer personaid;
    private String name;
    private String paternalLastName;
    private String maternalLastName;
    private Date dateOfBirth;
    private Address address;
    private String IdentityCard;
    
    private Integer clientId;
    private String email;
    private String telephone;
    private String occupation;
    private String status;
    
    private List<ListClientReferenceResponse> references;
        
}
