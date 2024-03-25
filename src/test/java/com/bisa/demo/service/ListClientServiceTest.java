/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.bisa.demo.service;

import com.bisa.demo.commons.TestUseCases;
import com.bisa.demo.dto.ListClientResponse;
import com.bisa.demo.enums.Accessibility;
import com.bisa.demo.exception.ErrorCode;
import com.bisa.demo.exception.ExceptionResponse;
import com.bisa.demo.repository.IPersonRepository;
import com.bisa.demo.repository.IReferenceRepository;
import com.bisa.demo.validator.ListClientValidator;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.bisa.demo.repository.ICreateClientRepository;
import com.bisa.demo.repository.ListClientRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author alepaco.com
 */
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ListClientServiceTest {

    private ListClientService service;
    private ListClientValidator listClientValidator;

    @PersistenceContext
    private EntityManager entityManager;

    @Mock
    private MultiLanguageMessagesService mlms;

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private ICreateClientRepository clientRepository;

    private ListClientRepositoryImpl listClientRepository;

    @Autowired
    private IReferenceRepository referenceRepository;

    TestUseCases testUseCases;

    public ListClientServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        listClientRepository= new ListClientRepositoryImpl(entityManager);
        
        listClientValidator = new ListClientValidator();

        testUseCases = new TestUseCases(personRepository,
                clientRepository, referenceRepository);

        testUseCases.fillBasic();

        service = new ListClientService(mlms, listClientValidator,
                listClientRepository, referenceRepository);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void listFailAccessibilityNull() throws Exception {
        String accessibility = null;

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.list(accessibility));

        assertEquals(ex.getCode(),
                ErrorCode.LIST_CLIENTS_BY_ACCESSIBILITY_FILTER_ACCESSIBILITY_IS_REQUIRED.getCode());
    }

    @Test
    public void listFailAccessibilityEmpty() throws Exception {
        String accessibility = "";

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.list(accessibility));

        assertEquals(ex.getCode(),
                ErrorCode.LIST_CLIENTS_BY_ACCESSIBILITY_FILTER_ACCESSIBILITY_IS_REQUIRED.getCode());
    }

    @Test
    public void listFailAccessibilityInvalid() throws Exception {
        String accessibility = "Cualquier valor random";

        ExceptionResponse ex = assertThrows(
                ExceptionResponse.class,
                () -> service.list(accessibility));

        assertEquals(ex.getCode(),
                ErrorCode.LIST_CLIENTS_BY_ACCESSIBILITY_FILTER_ACCESSIBILITY_IS_INVALID.getCode());
    }

    @Test
    public void listFailAccessibilityEqualsGood() throws Exception {
        String accessibility = Accessibility.BUENA.name();

        List<ListClientResponse> response = service.list(accessibility);

        assertTrue(testUseCases.validarUseCasesGood(response));
    }

    @Test
    public void listFailAccessibilityEqualsRegular() throws Exception {
        String accessibility = Accessibility.REGULAR.name();

        List<ListClientResponse> response = service.list(accessibility);

        assertTrue(testUseCases.validarUseCasesRegular(response));
    }

    @Test
    public void listFailAccessibilityEqualsBad() throws Exception {
        String accessibility = Accessibility.MALA.name();

        List<ListClientResponse> response = service.list(accessibility);

        assertTrue(testUseCases.validarUseCasesBad(response));
    }

    @Test
    public void listFailAccessibilityEqualsNull() throws Exception {
        String accessibility = Accessibility.NULA.name();

        List<ListClientResponse> response = service.list(accessibility);

        assertTrue(testUseCases.validarUseCasesNull(response));
    }

}
