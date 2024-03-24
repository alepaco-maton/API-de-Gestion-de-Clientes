/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.commons;

import com.bisa.demo.dto.ListClientReferenceResponse;
import com.bisa.demo.dto.ListClientResponse;
import com.bisa.demo.entity.Client;
import com.bisa.demo.entity.Reference;
import com.bisa.demo.repository.IReferenceRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author alepaco.com
 */
public class ListClientMapper {

    public static ListClientReferenceResponse mapperToDto(Reference model) {
        return new ListClientReferenceResponse(model.getId(),
                model.getPersonId().getId(),
                model.getPersonId().getFullName(),
                model.getPersonId().isClient(),
                (model.getReasonForElimination() != null),
                model.getReasonForElimination());
    }

    public static ListClientResponse mapperToDto(Client model, IReferenceRepository referenceRepository) {
        List<ListClientReferenceResponse> references = referenceRepository.
                findAllByClientIdId(model.getId()).
                stream().map(ref -> mapperToDto(ref)).
                collect(Collectors.toList());

        return new ListClientResponse(model.getPerson().getId(),
                model.getPerson().getName(),
                model.getPerson().getPaternalLastName(),
                model.getPerson().getMaternalLastName(),
                model.getPerson().getDateOfBirth(),
                model.getPerson().getAddress(),
                model.getPerson().getIdentityCard(),
                model.getId(), model.getEmail(),
                model.getTelephone(), model.getOccupation(),
                model.getStatus(), references);
    }

    public static List<ListClientResponse> mapperToDto(List<Client> models, IReferenceRepository referenceRepository) {
        return models.stream().map(cl
                        -> mapperToDto(cl, referenceRepository)).
                collect(Collectors.toList());

    }

}
