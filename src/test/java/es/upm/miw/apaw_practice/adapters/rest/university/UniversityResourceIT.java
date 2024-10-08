package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.University;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.UniversityPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class UniversityResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UniversityPersistence universityPersistence;

    @Test
    void testCreateNonExisting() {
        University university = new University("ox.ac.uk", "University of Oxford", true, 1, new ArrayList<>());
        createUniversity(university).expectStatus().is4xxClientError();
    }

    @Test
    void testCreateExisting() {
        University university = new University("usal.es", "Universidad de Salamanca", false, 9, new ArrayList<>());
        assertFalse(universityPersistence.existTopDomain(university.getTopDomain()));
        createUniversity(university).expectStatus().isCreated();
        assertTrue(universityPersistence.existTopDomain(university.getTopDomain()));
        University createdUniversity = universityPersistence.read(university.getTopDomain());
        assertEquals(university.getName(), createdUniversity.getName());
        assertEquals(university.getAllowsInternationalStudents(), createdUniversity.getAllowsInternationalStudents());
        assertEquals(university.getNumberOfFaculties(), createdUniversity.getNumberOfFaculties());
        assertEquals(university.getDegreesOffered().size(), createdUniversity.getDegreesOffered().size());
    }

    private WebTestClient.ResponseSpec createUniversity(University university) {
        return webTestClient
                .post()
                .uri(UniversityResource.UNIVERSITIES)
                .body(BodyInserters.fromValue(university))
                .exchange();
    }
}
