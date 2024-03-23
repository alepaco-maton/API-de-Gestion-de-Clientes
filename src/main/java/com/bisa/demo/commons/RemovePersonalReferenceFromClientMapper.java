/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.commons;

import com.bisa.demo.dto.RemovePersonalReferenceFromClientResponse;
import com.bisa.demo.entity.Reference;

/**
 *
 * @author alepaco.com
 */
public class RemovePersonalReferenceFromClientMapper {

    public static RemovePersonalReferenceFromClientResponse mapperToDto(Reference model) {
        return new RemovePersonalReferenceFromClientResponse(model.getId(),
                model.getClientId().getId(), model.getPersonId().getId(),
                model.getReasonForElimination());
    }
}
