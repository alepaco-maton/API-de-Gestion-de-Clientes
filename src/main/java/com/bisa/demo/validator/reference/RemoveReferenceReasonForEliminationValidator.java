/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator.reference;

import com.bisa.demo.commons.AppTools;
import com.bisa.demo.dto.RemovePersonalReferenceFromClientRequest;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.validator.IValidator;

/**
 *
 * @author alepaco.com
 */
public class RemoveReferenceReasonForEliminationValidator implements IValidator<RemovePersonalReferenceFromClientRequest> {
 
    @Override
    public ErrorCode validate(RemovePersonalReferenceFromClientRequest request) {
        if (AppTools.isBlank(request.getReasonForElimination())) {
            return ErrorCode.REMOVE_PERSONAL_REFERENCE_TO_CLIENT_REASON_FOR_ELIMINATION_IS_REQUIRED;
        }

        return ErrorCode.SUCCESSFUL;
    }

}
