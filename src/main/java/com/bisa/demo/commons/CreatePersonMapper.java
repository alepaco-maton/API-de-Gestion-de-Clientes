/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.commons;

import com.bisa.demo.dto.CreatePersonAddressRequest;
import com.bisa.demo.dto.CreatePersonRequest;
import com.bisa.demo.dto.CreatePersonResponse;
import com.bisa.demo.entity.Address;
import com.bisa.demo.entity.Person;

/**
 *
 * @author alepaco.com
 */
public class CreatePersonMapper {

    public static Person mapperToEntity(CreatePersonRequest dto) {
        return new Person(null, dto.getName(), dto.getPaternalLastName(),
                dto.getMaternalLastName(), dto.getDateOfBirth(),
                mapperToEntityAddress(dto.getAddress()), dto.getIdentityCard(), null);
    }

    //TODO no esta definido quese tiene que retornar por tanto se retorna un objeto simple
    public static CreatePersonResponse mapperToDto(Person model) {
        return new CreatePersonResponse(model.getId(), model.getName(), 
                model.getPaternalLastName(), model.getMaternalLastName());
    }

    private static Address mapperToEntityAddress(CreatePersonAddressRequest address) {
        return new Address(address.getGeographicLocation(),
                address.getAreaNeighborhoodStreet(),
                address.getAddressNumber(),
                address.getReference());
    }

}
