/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.commons;

import com.bisa.demo.dto.CreateClientRequest;
import com.bisa.demo.dto.CreateClientResponse;
import com.bisa.demo.entity.Client;
import com.bisa.demo.enums.ClientStatus;
import com.bisa.demo.repository.IPersonRepository;

/**
 *
 * @author alepaco.com
 */
public class CreateClientMapper {

    public static Client mapperToEntity(CreateClientRequest dto, IPersonRepository personRepository) {
        return new Client(null, personRepository.findById(dto.getPersonId()).orElse(null), 
                dto.getEmail(), dto.getTelephone(), dto.getOccupation(),
                ClientStatus.CREATED);
    }

    public static CreateClientResponse mapperToDto(Client model) {
        return new CreateClientResponse(model.getId(), model.getPerson().getFullName(), 
                model.getEmail(), 
                model.getTelephone(), 
                model.getOccupation(), 
                model.getStatus());
    }

}
