/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.commons;

import com.bisa.demo.dto.AddPersonalReferenceToClientRequest;
import com.bisa.demo.dto.AddPersonalReferenceToClientResponse;
import com.bisa.demo.entity.Reference;
import com.bisa.demo.repository.IClientRepository;
import com.bisa.demo.repository.IPersonRepository;

/**
 *
 * @author alepaco.com
 */
public class AddPersonalReferenceToClientMapper {

    public static Reference mapperToEntity(AddPersonalReferenceToClientRequest dto,
            IClientRepository clientRepository, IPersonRepository personRepository) {
        return new Reference(null,
                clientRepository.findById(dto.getClientId()).orElse(null),
                personRepository.findById(dto.getPersonId()).orElse(null),
                null);
    }

    public static AddPersonalReferenceToClientResponse mapperToDto(Reference model) {
        return new AddPersonalReferenceToClientResponse(model.getId(),
                model.getClientId().getId(),
                model.getPersonId().getId());
    }
}
