/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator.createperson;

import com.bisa.demo.validator.*;
import com.bisa.demo.commons.AppTools;
import com.bisa.demo.dto.CreatePersonAddressRequest;
import com.bisa.demo.dto.CreatePersonRequest;
import com.bisa.demo.exception.ErrorCode;

/**
 *
 * @author alepaco.com
 */
public class CreatePersonAddressValidator implements IValidator<CreatePersonRequest> {

    @Override
    public ErrorCode validate(CreatePersonRequest request) {
        CreatePersonAddressRequest address = request.getAddress();

        if (address == null) {
            return ErrorCode.CREATE_PERSON_ADDRESS_IS_REQUIRED;
        }

        if (AppTools.isBlank(address.getGeographicLocation())) {
            return ErrorCode.CREATE_PERSON_GEOGRAPHIC_LOCATION_IS_REQUIRED;
        }

        if (AppTools.isBlank(address.getAreaNeighborhoodStreet())) {
            return ErrorCode.CREATE_PERSON_AREA_NEIGHBORHOOD_STREET_IS_REQUIRED;
        }

        if (AppTools.isBlank(address.getAddressNumber())) {
            return ErrorCode.CREATE_PERSON_ADDRESS_NUMBER_IS_REQUIRED;
        }

        if (AppTools.isBlank(address.getReference())) {
            return ErrorCode.CREATE_PERSON_REFERENCE_IS_REQUIRED;
        }

        return ErrorCode.SUCCESSFUL;
    }

}
