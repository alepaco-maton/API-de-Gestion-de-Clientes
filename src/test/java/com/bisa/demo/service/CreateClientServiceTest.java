/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.bisa.demo.service;

import com.bisa.demo.commons.CreateClientMapper;
import com.bisa.demo.dto.CreateClientRequest;
import com.bisa.demo.dto.CreateClientResponse;
import com.bisa.demo.entity.Client;
import com.bisa.demo.entity.Person;
import com.bisa.demo.enums.ClientStatus;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.repository.IReferenceRepository;
import com.bisa.demo.validator.CreateClientValidator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.bisa.demo.repository.ICreateClientRepository;

/**
 *
 * @author alepaco.com
 */
@SpringBootTest
public class CreateClientServiceTest {

    private CreateClientService service;
    private CreateClientValidator createClientValidator;

    @Mock
    private MultiLanguageMessagesService mlms;

    @Mock
    private ICreateClientRepository clientRepository;

    @Mock
    private IPersonRepository personRepository;

    @Mock
    private IReferenceRepository referenceRepository;

    public CreateClientServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        createClientValidator = new CreateClientValidator(personRepository,
                clientRepository);

        service = new CreateClientService(mlms, createClientValidator,
                clientRepository, personRepository);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void createFailByRequestEmailNull() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail(null);
        request.setOccupation("Cajero");
        request.setTelephone("77652889");
        request.setPersonId(1);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_EMAIL_IS_REQUIRED.getCode());
    }

    @Test
    public void createFailByRequestEmailEmpty() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("");
        request.setOccupation("Cajero");
        request.setTelephone("77652889");
        request.setPersonId(1);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_EMAIL_IS_REQUIRED.getCode());
    }

    @Test
    public void createFailByRequestTelephoneNull() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation("Cajero");
        request.setTelephone(null);
        request.setPersonId(1);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_TELEPHONE_IS_REQUIRED.getCode());
    }

    @Test
    public void createFailByRequestTelephoneEmpty() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation("Cajero");
        request.setTelephone("");
        request.setPersonId(1);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_TELEPHONE_IS_REQUIRED.getCode());
    }

    @Test
    public void createFailByRequestOccupationNull() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation(null);
        request.setTelephone("77325584");
        request.setPersonId(1);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_OCCUPATION_IS_REQUIRED.getCode());
    }

    @Test
    public void createFailByRequestOccupationEmpty() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation("");
        request.setTelephone("77325584");
        request.setPersonId(1);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_OCCUPATION_IS_REQUIRED.getCode());
    }

    @Test
    public void createFailByRequestPersonaIdNull() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation("Cajero");
        request.setTelephone("77325584");
        request.setPersonId(null);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_PERSON_IS_REQUIRED.getCode());
    }

    @Test
    public void createFailByRequestPersonaIdInvalid() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation("Cajero");
        request.setTelephone("77325584");
        request.setPersonId(-1);

        Person person = null;

        Mockito.when(personRepository.findById(request.getPersonId())).
                thenReturn(Optional.ofNullable(person));

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_PERSON_IS_INVALID.getCode());
    }

    @Test
    public void createFailByRequestPersonLessThan20YearsOld() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation("Cajero");
        request.setTelephone("77325584");
        request.setPersonId(1);

        Calendar cal = Calendar.getInstance();
        // con esto se calcula una fecha de nacimiento menor a 20 años
        cal.add(Calendar.YEAR, -15);

        Date dateOfBirth = cal.getTime();

        Person person = new Person();
        person.setId(request.getPersonId());
        person.setDateOfBirth(dateOfBirth);

        Optional<Person> optional = Optional.of(person);

        Mockito.when(personRepository.findById(request.getPersonId())).
                thenReturn(optional);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_PERSON_LESS_THAN_20_YEARS_OLD.getCode());
    }

    @Test
    public void createFailByRequestPersonAssociatedOtherClient() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation("Cajero");
        request.setTelephone("77325584");
        request.setPersonId(1);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20);

        Date dateOfBirth = cal.getTime();

        Person person = new Person();
        person.setId(request.getPersonId());
        person.setDateOfBirth(dateOfBirth);

        Optional<Person> optional = Optional.of(person);

        Mockito.when(personRepository.findById(request.getPersonId())).
                thenReturn(optional);

        List<Client> listClients = new ArrayList<>();
        listClients.add(new Client());

        Mockito.when(clientRepository.findAllByPersonId(
                request.getPersonId())).
                thenReturn(listClients);

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.create(request));

        assertEquals(ex.getCode(),
                ErrorCode.CREATE_CLIENT_PERSON_IS_ASSOCIATED_WITH_ANOTHER_CUSTOMER.getCode());
    }

    @Test
    public void createSuccesssful() throws Exception {
        CreateClientRequest request = new CreateClientRequest();
        request.setEmail("alepaco.maton@gmail.com");
        request.setOccupation("Cajero");
        request.setTelephone("77325584");
        request.setPersonId(1);

        //PERSONA MAYOR O IGUAL a 20 años (Un cliente no puede tener menos de 20 años.)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20);

        Date dateOfBirth = cal.getTime();

        Person person = new Person();
        person.setId(request.getPersonId());
        person.setDateOfBirth(dateOfBirth);

        Optional<Person> optional = Optional.of(person);

        Mockito.when(personRepository.findById(request.getPersonId())).
                thenReturn(optional);

        List<Client> listClients = new ArrayList<>();

        Mockito.when(clientRepository.findAllByPersonId(
                request.getPersonId())).
                thenReturn(listClients);

        Client model = new Client();
        model.setId(1);
        model.setPerson(person);
        model.setEmail(request.getEmail());
        model.setStatus(ClientStatus.CREATED);
        model.setOccupation(request.getOccupation());
        model.setTelephone(request.getTelephone());

        Mockito.when(clientRepository.save(
                CreateClientMapper.mapperToEntity(
                        request, personRepository))).
                thenReturn(model);

        CreateClientResponse expResult = new CreateClientResponse();
        expResult.setId(model.getId());

        CreateClientResponse result = service.create(request);

        assertEquals(expResult.getId(), result.getId());
    }

}
