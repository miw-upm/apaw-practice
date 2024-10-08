package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.University;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.DegreePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.UniversityPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class UniversityResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UniversityPersistence universityPersistence;

    @Autowired
    private DegreePersistence degreePersistence;

    @Test
    void testCreateWithExistingTopDomain() {
        assertTrue(universityPersistence.existTopDomain("ox.ac.uk"));
        University university = new University("ox.ac.uk", "University of Oxford", true, 1, List.of());
        createUniversity(university).expectStatus().is4xxClientError();
    }

    @Test
    void testCreate() {
        assertFalse(universityPersistence.existTopDomain("usal.es"));
        University university = new University("usal.es", "Universidad de Salamanca", false, 9,
                List.of(degreePersistence.read(2000), degreePersistence.read(2001), degreePersistence.read(2002)));
        assertFalse(universityPersistence.existTopDomain(university.getTopDomain()));
        createUniversity(university).expectStatus().isCreated();
        assertTrue(universityPersistence.existTopDomain(university.getTopDomain()));
        University createdUniversity = universityPersistence.read(university.getTopDomain());
        assertEquals(university.getName(), createdUniversity.getName());
        assertEquals(university.getAllowsInternationalStudents(), createdUniversity.getAllowsInternationalStudents());
        assertEquals(university.getNumberOfFaculties(), createdUniversity.getNumberOfFaculties());
        assertEquals(university.getDegreesOffered(), createdUniversity.getDegreesOffered());
    }

    private WebTestClient.ResponseSpec createUniversity(University university) {
        return webTestClient
                .post()
                .uri(UniversityResource.UNIVERSITIES)
                .body(BodyInserters.fromValue(university))
                .exchange();
    }
}
