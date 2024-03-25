/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.commons;

import com.bisa.demo.dto.ListClientResponse;
import com.bisa.demo.entity.Address;
import com.bisa.demo.entity.Client;
import com.bisa.demo.entity.Person;
import com.bisa.demo.entity.Reference;
import com.bisa.demo.enums.ClientStatus;
import com.bisa.demo.repository.ICreateClientRepository;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.repository.IReferenceRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author alepaco.com
 */
@Log4j2
public class TestUseCases {

    private final IPersonRepository personRepository;
    private final ICreateClientRepository clientRepository;
    private final IReferenceRepository referenceRepository;

    private List<Person> persons;
    private List<Client> clients;

    public TestUseCases(IPersonRepository personRepository,
            ICreateClientRepository clientRepository,
            IReferenceRepository referenceRepository) {
        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
        this.referenceRepository = referenceRepository;
    }

    public void fillPersons() {
        persons = new ArrayList<>();
        personRepository.deleteAll();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20);
        Date dateOfBirth = cal.getTime();

        persons.add(personRepository.save(new Person(1, "Juan", "Perez",
                "Mamani", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(2, "Isidoro", "Chuque",
                "Zans", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(3, "Olivia", "Palma",
                "Ramirez", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(4, "Ulices", "Odiseo",
                "Narnia", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(5, "Julio", "Upanqui",
                "Jym", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(6, "Yuri", "Choque",
                "Tayu", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(7, "Teresa", "Jun",
                "Kila", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(8, "Jimena", "Elizondo",
                "Rojas", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(9, "Miguel", "Yatu",
                "LLosa", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(10, "Maria", "Dolores",
                "Sarate", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(11, "Kim", "Yung",
                "Clop", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(12, "Magdalena", "Utis",
                "Uriarte", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(13, "Josefina", "Pomacusi",
                "Suarez", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));
    }

    public void fillClients() {
        clients = new ArrayList<>();

        clientRepository.deleteAll();

        Person persona = persons.get(0);
        clients.add(clientRepository.save(new Client(1,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(1);
        clients.add(clientRepository.save(new Client(2,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(3);
        clients.add(clientRepository.save(new Client(3,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(4);
        clients.add(clientRepository.save(new Client(4,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(6);
        clients.add(clientRepository.save(new Client(5,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(7);
        clients.add(clientRepository.save(new Client(6,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(9);
        clients.add(clientRepository.save(new Client(7,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(10);
        clients.add(clientRepository.save(new Client(8,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(11);
        clients.add(clientRepository.save(new Client(9,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

        persona = persons.get(12);
        clients.add(clientRepository.save(new Client(10,
                persona,
                persona.getName() + "@gmail.com",
                "3356322",
                "Albañil",
                ClientStatus.CREATED)));

    }

    //Personas NO clientes PersonaID[3, 6 y 9] las demas son personas son
    //clientes PersonaID[1,2,4,5,7,8,10,11,12,13]
    //Casos de prueba: 
    //cliente 1 = 2 referncias que no son clientes, PersonasID(2 y 4) 
    //cliente 2 = 2 referncias que son clientes PersonasID(3 y 6) 
    //cliente 3 = 2 referncias, un cliente y otro no cliente PersonasID(1 y 6) 
    //cliente 4 = 3 referncias, 1 cliente y 2 no clientes PersonasID(1, 2, y 3) 
    //cliente 5 = 4 referncias, 2 clientes y 2 no cliente PersonasID(1, 2, 3 y 6) 
    //cliente 6 = 1 referncias 1 cliente PersonasID(9) 
    //cliente 7 = 3 referncias que son clientes PersonasID(3, 6 y 9) 
    //cliente 8 = 3 referncias 1 no cliente y 2 clientes PersonasID(1, 6 y 9) 
    //cliente 9 = 1 referncias 1 no cliente PersonasID(1) 
    //cliente 10 = 0 referncias
    public void fillReferences() {
        referenceRepository.deleteAll();

        referenceRepository.save(new Reference(1,
                clients.get(0),
                persons.get(1),
                null));

        referenceRepository.save(new Reference(2,
                clients.get(0),
                persons.get(3),
                null));

        //cliente 2 = 2 referncias que son clientes, PersonasID (3 y 6) 
        referenceRepository.save(new Reference(3,
                clients.get(1),
                persons.get(2),
                null));

        referenceRepository.save(new Reference(4,
                clients.get(1),
                persons.get(5),
                null));

        // cliente 3 = 2 referncias, un cliente y otro no cliente, PersonasID (1 y 6) 
        referenceRepository.save(new Reference(5,
                clients.get(2),
                persons.get(0),
                null));

        referenceRepository.save(new Reference(6,
                clients.get(2),
                persons.get(5),
                null));

        // cliente 4 = 3 referncias, 1 cliente y 2 no clientes, PersonasID (1, 2, y 3) 
        referenceRepository.save(new Reference(7,
                clients.get(3),
                persons.get(0),
                null));

        referenceRepository.save(new Reference(8,
                clients.get(3),
                persons.get(1),
                null));

        referenceRepository.save(new Reference(9,
                clients.get(3),
                persons.get(2),
                null));

        // cliente 5 = 4 referncias, 2 clientes y 2 no cliente, PersonasID (1, 2, 3 y 6) 
        referenceRepository.save(new Reference(10,
                clients.get(4),
                persons.get(0),
                null));

        referenceRepository.save(new Reference(11,
                clients.get(4),
                persons.get(1),
                null));

        referenceRepository.save(new Reference(12,
                clients.get(4),
                persons.get(2),
                null));

        referenceRepository.save(new Reference(13,
                clients.get(4),
                persons.get(5),
                null));

        //cliente 6 = 1 referncias 1 cliente PersonasID(9) 
        referenceRepository.save(new Reference(14,
                clients.get(5),
                persons.get(8),
                null));

        // cliente 7 = 3 referncias que son clientes, PersonasID (3, 6 y 9) 
        referenceRepository.save(new Reference(15,
                clients.get(6),
                persons.get(2),
                null));

        referenceRepository.save(new Reference(16,
                clients.get(6),
                persons.get(5),
                null));

        referenceRepository.save(new Reference(17,
                clients.get(6),
                persons.get(8),
                null));

        // cliente 8 = 3 referncias 1 no cliente y 2 clientes, PersonasID (1, 6 y 9)  
        referenceRepository.save(new Reference(18,
                clients.get(7),
                persons.get(0),
                null));

        referenceRepository.save(new Reference(19,
                clients.get(7),
                persons.get(5),
                null));

        referenceRepository.save(new Reference(20,
                clients.get(7),
                persons.get(8),
                null));

        // cliente 9 = 1 referncias 1 no cliente, PersonasID (1) 
        referenceRepository.save(new Reference(21,
                clients.get(8),
                persons.get(0),
                null));

        // cliente 10 = 0 referncias 
    }

    public void fillBasic() {
        fillPersons();
        fillClients();
        fillReferences();
    }

    // Personas NO clientes PersonaID[3, 6 y 9] las demas son personas son
    // clientes PersonaID[1,2,4,5,7,8,10,11,12,13]
    // accesibilidad BUENA
    // Regla 1 = TOTAL REFERENCIAS (>= 2) y REFERENCIAS DE TIPO CLIENTE (>=2)
    // Regla 2 = TOTAL REFERENCIAS (>= 3) y REFERENCIAS DE TIPO CLIENTE (>=1)
    // Los clientes a retornar con ID 
    // [
    // cliente ID = 2 (regla 1) PersonasID(3 y 6) , 
    // cliente ID = 4 (regla 1 y 2) PersonasID(1, 2, y 3) , 
    // cliente ID = 5 (regla 1 y 2) PersonasID(1, 2, 3 y 6),
    // cliente ID = 7 (regla 1 y 2) PersonasID(3, 6 y 9),
    // cliente ID = 8 (regla 1 y 2) PersonasID(1, 6 y 9) 
    // ]
    public boolean validarUseCasesGood(List<ListClientResponse> response) {
        List<Integer> ids = response.stream().
                map(element -> element.getClientId()).sorted().
                collect(Collectors.toList());

        return response.size() == 5
                && ids.get(0).equals(clients.get(1).getId())
                && ids.get(1).equals(clients.get(3).getId())
                && ids.get(2).equals(clients.get(4).getId())
                && ids.get(3).equals(clients.get(6).getId())
                && ids.get(4).equals(clients.get(7).getId());
    }

    // Personas NO clientes PersonaID[3, 6 y 9] las demas son personas son
    // clientes PersonaID[1,2,4,5,7,8,10,11,12,13]
    // accesibilidad REGULAR
    // Regla 1 = TOTAL REFERENCIAS (>= 2) y REFERENCIAS DE TIPO CLIENTE (0)
    // Regla 2 = TOTAL REFERENCIAS (1) y REFERENCIAS DE TIPO CLIENTE (1)
    // Los clientes a retornar con ID 
    // [
    // cliente ID = 1 (regla 1) PersonasID(2 y 4) , 
    // cliente ID = 6 (regla 2) PersonasID(9) , 
    // ]
    public boolean validarUseCasesRegular(List<ListClientResponse> response) {
        List<Integer> ids = response.stream().
                map(element -> element.getClientId()).sorted().
                collect(Collectors.toList());

        return response.size() == 2
                && ids.get(0).equals(clients.get(0).getId())
                && ids.get(1).equals(clients.get(5).getId());
    }

    // Personas NO clientes PersonaID[3, 6 y 9] las demas son personas son
    // clientes PersonaID[1,2,4,5,7,8,10,11,12,13]
    // accesibilidad MALA
    // Regla 1 = TOTAL REFERENCIAS (1) y REFERENCIAS DE TIPO CLIENTE (0)
    // Los clientes a retornar con ID 
    // [
    // cliente ID = 9 (regla 1) PersonasID(1)
    // ]
    public boolean validarUseCasesBad(List<ListClientResponse> response) {
        List<Integer> ids = response.stream().
                map(element -> element.getClientId()).sorted().
                collect(Collectors.toList());

        return response.size() == 1
                && ids.get(0).equals(clients.get(8).getId());
    }

    // Personas NO clientes PersonaID[3, 6 y 9] las demas son personas son
    // clientes PersonaID[1,2,4,5,7,8,10,11,12,13]
    // accesibilidad MALA
    // Regla 1 = TOTAL REFERENCIAS (0) y REFERENCIAS DE TIPO CLIENTE (0)
    // Los clientes a retornar con ID 
    // [
    // cliente ID = 10 (regla 1) 0 referncias
    // ]
    public boolean validarUseCasesNull(List<ListClientResponse> response) {
        List<Integer> ids = response.stream().
                map(element -> element.getClientId()).sorted().
                collect(Collectors.toList());

        return response.size() == 1
                && ids.get(0).equals(clients.get(9).getId());
    }

}
