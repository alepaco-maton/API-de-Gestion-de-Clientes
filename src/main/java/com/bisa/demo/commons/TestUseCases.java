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
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20);
        Date dateOfBirth = cal.getTime();

        persons.add(personRepository.save(new Person(1, "Juan", "Perez",
                "Mamani", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(2, "Isidoro", "Chuque",
                "Zans", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(3, "Olivia", "Palma",
                "Ramirez", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(4, "Ulices", "Odiseo",
                "Narnia", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(5, "Julio", "Upanqui",
                "Jym", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(6, "Yuri", "Choque",
                "Tayu", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(7, "Teresa", "Jun",
                "Kila", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(8, "Jimena", "Elizondo",
                "Rojas", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(9, "Miguel", "Yatu",
                "LLosa", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", false)));

        persons.add(personRepository.save(new Person(10, "Maria", "Dolores",
                "Sarate", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(11, "Kim", "Yung",
                "Clop", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(12, "Magdalena", "Utis",
                "Uriarte", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));

        persons.add(personRepository.save(new Person(13, "Josefina", "Pomacusi",
                "Suarez", dateOfBirth,
                new Address("latitude,longitude",
                        "Plan 3000",
                        "15",
                        "A dos cuadras de la plaza del barrio."),
                "8865152", true)));
    }

    public void fillClients() {
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
    //cliente 1 = 2 referncias que son clientes, PersonasID(2 y 4) 
    //cliente 2 = 2 referncias que no son clientes PersonasID(3 y 6) 
    //cliente 3 = 2 referncias, un cliente y otro no cliente PersonasID(1 y 6) 
    //cliente 4 = 3 referncias, 1 cliente y 2 no clientes PersonasID(1, 3 y 6) 
    //cliente 5 = 4 referncias, 2 clientes y 2 no cliente PersonasID(1, 2, 3 y 6) 
    //cliente 6 = 1 referncias 1 cliente PersonasID(1) 
    //cliente 7 = 3 referncias que son clientes PersonasID(1, 5 y 8) 
    //cliente 8 = 3 referncias que no son clientes PersonasID(3, 6 y 9) 
    //cliente 9 = 1 referncias 1 no cliente PersonasID(9) 
    //cliente 10 = 0 referncias
    public void fillReferences() {
        //cliente 1 = 2 referncias que son clientes, PersonasID(2 y 4) 
        referenceRepository.save(new Reference(1,
                clients.get(0),
                persons.get(1),
                null));

        referenceRepository.save(new Reference(2,
                clients.get(0),
                persons.get(3),
                null));

        //cliente 2 = 2 referncias que no son clientes PersonasID(3 y 6)
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

        //cliente 4 = 3 referncias, 1 cliente y 2 no clientes PersonasID(1, 3 y 6) 
        referenceRepository.save(new Reference(7,
                clients.get(3),
                persons.get(0),
                null));

        referenceRepository.save(new Reference(8,
                clients.get(3),
                persons.get(2),
                null));

        referenceRepository.save(new Reference(9,
                clients.get(3),
                persons.get(5),
                null));

        //cliente 5 = 4 referncias, 2 clientes y 2 no cliente PersonasID(1, 2, 3 y 6) 
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

        //cliente 6 = 1 referncias 1 cliente PersonasID(1) 
        referenceRepository.save(new Reference(14,
                clients.get(5),
                persons.get(0),
                null));

        //cliente 7 = 3 referncias que son clientes PersonasID(1, 5 y 8) 
        referenceRepository.save(new Reference(15,
                clients.get(6),
                persons.get(0),
                null));

        referenceRepository.save(new Reference(16,
                clients.get(6),
                persons.get(4),
                null));

        referenceRepository.save(new Reference(17,
                clients.get(6),
                persons.get(7),
                null));

        //cliente 8 = 3 referncias que no son clientes PersonasID(3, 6 y 9) 
        referenceRepository.save(new Reference(18,
                clients.get(7),
                persons.get(2),
                null));

        referenceRepository.save(new Reference(19,
                clients.get(7),
                persons.get(5),
                null));

        referenceRepository.save(new Reference(20,
                clients.get(7),
                persons.get(8),
                null));

        //cliente 9 = 1 referncias 1 no cliente PersonasID(9) 
        referenceRepository.save(new Reference(21,
                clients.get(8),
                persons.get(8),
                null));

        // cliente 10 = 0 referncias 
    }

    public void fillBasic() {
        referenceRepository.deleteAll();
        clientRepository.deleteAll();
        personRepository.deleteAll();

        persons = new ArrayList<>();
        clients = new ArrayList<>();

        fillPersons();
        fillClients();
        fillReferences();
    }

    public String info() {
        return "Informacion: \n"
                + "PersonaID\n"
                + "["
                + "PersonaId: " + persons.get(0).getId() + ", es ClienteId: " + clients.get(0).getId() + " \n"
                + "PersonaId: " + persons.get(1).getId() + ", es ClienteId: " + clients.get(1).getId() + " \n"
                + "PersonaId: " + persons.get(2).getId() + ", NO es Cliente \n"
                + "PersonaId: " + persons.get(3).getId() + ", es ClienteId: " + clients.get(2).getId() + " \n"
                + "PersonaId: " + persons.get(4).getId() + ", es ClienteId: " + clients.get(3).getId() + " \n"
                + "PersonaId: " + persons.get(5).getId() + ", NO es Cliente \n"
                + "PersonaId: " + persons.get(6).getId() + ", es ClienteId: " + clients.get(4).getId() + " \n"
                + "PersonaId: " + persons.get(7).getId() + ", es ClienteId: " + clients.get(5).getId() + " \n"
                + "PersonaId: " + persons.get(8).getId() + ", NO es Cliente \n"
                + "PersonaId: " + persons.get(9).getId() + ", es ClienteId: " + clients.get(6).getId() + " \n"
                + "PersonaId: " + persons.get(10).getId() + ", es ClienteId: " + clients.get(7).getId() + " \n"
                + "PersonaId: " + persons.get(11).getId() + ", es ClienteId: " + clients.get(8).getId() + " \n"
                + "PersonaId: " + persons.get(12).getId() + ", es ClienteId: " + clients.get(9).getId() + " \n"
                + "]\n"
                + "\n"
                + "Casos de prueba (clientes y sus referencias): \n"
                + "cliente " + clients.get(0).getId() + " => 2 referncias que son clientes, PersonasID(" + persons.get(1).getId() + " y " + persons.get(3).getId() + ") \n"
                + "cliente " + clients.get(1).getId() + " => 2 referncias que no son clientes PersonasID(" + persons.get(2).getId() + " y " + persons.get(5).getId() + ") \n"
                + "cliente " + clients.get(2).getId() + " => 2 referncias, un cliente y otro no cliente PersonasID(" + persons.get(0).getId() + " y " + persons.get(5).getId() + ") \n"
                + "cliente " + clients.get(3).getId() + " => 3 referncias, 1 cliente y 2 no clientes PersonasID(" + persons.get(0).getId() + ", " + persons.get(2).getId() + " y " + persons.get(5).getId() + ") \n"
                + "cliente " + clients.get(4).getId() + " => 4 referncias, 2 clientes y 2 no cliente PersonasID(" + persons.get(0).getId() + ", " + persons.get(1).getId() + ", " + persons.get(2).getId() + " y " + persons.get(5).getId() + ") \n"
                + "cliente " + clients.get(5).getId() + " => 1 referncias 1 cliente PersonasID(" + persons.get(1).getId() + ") \n"
                + "cliente " + clients.get(6).getId() + " => 3 referncias que son clientes PersonasID(" + persons.get(0).getId() + ", " + persons.get(4).getId() + " y " + persons.get(7).getId() + ") \n"
                + "cliente " + clients.get(7).getId() + " => 3 referncias que no son clientes PersonasID(" + persons.get(2).getId() + ", " + persons.get(5).getId() + " y " + persons.get(8).getId() + ") \n"
                + "cliente " + clients.get(8).getId() + " => 1 referncias 1 no cliente PersonasID(" + persons.get(8).getId() + ") \n"
                + "cliente " + clients.get(9).getId() + " => 0 referncias \n"
                + "\n"
                + "Accesibilidad = BUENA\n"
                + "Regla 1 = TOTAL REFERENCIAS (>= 2) y REFERENCIAS DE TIPO CLIENTE (>=2)\n"
                + "Regla 2 = TOTAL REFERENCIAS (>= 3) y REFERENCIAS DE TIPO CLIENTE (>=1)\n"
                + "\n"
                + "Los clientes a retornar son: \n"
                + "[\n"
                + "cliente ID = " + clients.get(0).getId() + " (regla 1) PersonasID(" + persons.get(1).getId() + ", " + persons.get(3).getId() + ") , \n"
                + "cliente ID = " + clients.get(3).getId() + " (regla 2) PersonasID(" + persons.get(0).getId() + ", " + persons.get(2).getId() + ", , " + persons.get(5).getId() + ") , \n"
                + "cliente ID = " + clients.get(4).getId() + " (regla 1 y 2) PersonasID(" + persons.get(0).getId() + ", " + persons.get(1).getId() + ", " + persons.get(2).getId() + " , " + persons.get(5).getId() + "),\n"
                + "cliente ID = " + clients.get(6).getId() + " (regla 1 y 2) PersonasID(" + persons.get(0).getId() + ", " + persons.get(4).getId() + " , " + persons.get(7).getId() + ") , \n"
                + "]\n "
                + "\n"
                + "\n"
                + "Accesibilidad = REGULAR\n"
                + "Regla 1 = TOTAL REFERENCIAS (>= 2) y REFERENCIAS DE TIPO CLIENTE (0)\n"
                + "Regla 2 = TOTAL REFERENCIAS (1) y REFERENCIAS DE TIPO CLIENTE (1)\n"
                + "Los clientes a retornar son: \n"
                + "[\n"
                + "cliente ID = " + clients.get(1).getId() + " (regla 1) PersonasID(" + persons.get(2).getId() + ", " + persons.get(5).getId() + ") , \n"
                + "cliente ID = " + clients.get(5).getId() + " (regla 2) PersonasID(" + persons.get(0).getId() + ") , \n"
                + "cliente ID = " + clients.get(7).getId() + " (regla 1) PersonasID(" + persons.get(2).getId() + ", " + persons.get(5).getId() + ", " + persons.get(8).getId() + ") , \n"
                + "]\n"
                + "\n"
                + "Accesibilidad = MALA\n"
                + "Regla 1 = TOTAL REFERENCIAS (1) y REFERENCIAS DE TIPO CLIENTE (0)\n"
                + "Los clientes a retornar son: \n"
                + "[\n"
                + "cliente ID = " + clients.get(8).getId() + " (regla 1) PersonasID(" + persons.get(8).getId() + ")\n"
                + "]\n"
                + "\n"
                + "Accesibilidad = NULA\n"
                + "Regla 1 = TOTAL REFERENCIAS (0) y REFERENCIAS DE TIPO CLIENTE (0)\n"
                + "Los clientes a retornar son: \n"
                + "[\n"
                + "cliente ID = " + clients.get(9).getId() + " (regla 1) 0 referncias\n"
                + "]\n"
                + "";
    }

    public boolean validarUseCasesGood(List<ListClientResponse> response) {
        List<Integer> ids = response.stream().
                map(element -> element.getClientId()).sorted().
                collect(Collectors.toList());

        return response.size() == 4
                && ids.get(0).equals(clients.get(0).getId())
                && ids.get(1).equals(clients.get(3).getId())
                && ids.get(2).equals(clients.get(4).getId())
                && ids.get(3).equals(clients.get(6).getId());
    }

    public boolean validarUseCasesRegular(List<ListClientResponse> response) {
        List<Integer> ids = response.stream().
                map(element -> element.getClientId()).sorted().
                collect(Collectors.toList());

        return response.size() == 3
                && ids.get(0).equals(clients.get(1).getId())
                && ids.get(1).equals(clients.get(5).getId())
                && ids.get(2).equals(clients.get(7).getId());
    }

    public boolean validarUseCasesBad(List<ListClientResponse> response) {
        List<Integer> ids = response.stream().
                map(element -> element.getClientId()).sorted().
                collect(Collectors.toList());

        return response.size() == 1
                && ids.get(0).equals(clients.get(8).getId());
    }

    public boolean validarUseCasesNull(List<ListClientResponse> response) {
        List<Integer> ids = response.stream().
                map(element -> element.getClientId()).sorted().
                collect(Collectors.toList());

        return response.size() == 1
                && ids.get(0).equals(clients.get(9).getId());
    }

}
