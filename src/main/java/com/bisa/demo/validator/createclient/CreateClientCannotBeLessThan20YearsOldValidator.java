/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.validator.createclient;

import com.bisa.demo.commons.AppTools;
import com.bisa.demo.dto.CreateClientRequest;
import com.bisa.demo.entity.Client;
import com.bisa.demo.entity.Person;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.repository.IClientRepository;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.validator.IValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author alepaco.com
 */
public class CreateClientCannotBeLessThan20YearsOldValidator implements IValidator<CreateClientRequest> {

    IPersonRepository personRepository;
    IClientRepository clientRepository;

    public CreateClientCannotBeLessThan20YearsOldValidator(IPersonRepository personRepository, IClientRepository clientRepository) {
        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
    }
    
    @Override
    public ErrorCode validate(CreateClientRequest request) {
        if (request.getPersonId() == null) {
            return ErrorCode.CREATE_CLIENT_PERSON_IS_REQUIRED;
        }
        
        Optional<Person> optional = personRepository.findById(request.getPersonId());
        
        if(!optional.isPresent()) {
            return ErrorCode.CREATE_CLIENT_PERSON_IS_INVALID;
        }
        
        Person person = optional.get();
        
        if (!isUnder20YearsOld(AppTools.convertToDateToLocalDate(person.getDateOfBirth()))) {
            return ErrorCode.CREATE_CLIENT_PERSON_LESS_THAN_20_YEARS_OLD;
        }
        
        List<Client> list = clientRepository.findAllByPersonId(request.getPersonId());
        
        if (!list.isEmpty()) {
            return ErrorCode.CREATE_CLIENT_PERSON_IS_ASSOCIATED_WITH_ANOTHER_CUSTOMER;
        }
 
        return ErrorCode.SUCCESSFUL;
    }
    
    public boolean isUnder20YearsOld(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();

        // Restar 20 años a la fecha actual para obtener la fecha máxima válida
        LocalDate maxValidBirthDate = currentDate.minusYears(20);

        // Comparar la fecha de nacimiento con la fecha máxima válida
        return birthDate.isAfter(maxValidBirthDate);
    }
    
}
