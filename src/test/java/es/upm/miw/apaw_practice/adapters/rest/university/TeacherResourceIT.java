package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.TeacherPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class TeacherResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TeacherPersistence teacherPersistence;

    @Test
    void testDeleteNonExisting() {
        deleteTeacher("ABC123").expectStatus().isOk();
    }

    @Test
    void testDeleteExisting() {
        teacherPersistence.create(new Teacher("0001", LocalDate.of(1990, 1, 2), "Taylor", null));
        assertTrue(teacherPersistence.existNationalId("0001"));
        deleteTeacher("0001").expectStatus().isOk();
        assertFalse(teacherPersistence.existNationalId("0001"));
    }

    @Test
    void testDeleteIsIdempotent() {
        teacherPersistence.create(new Teacher("0002", LocalDate.of(1984, 2, 15), "Simpson", null));
        deleteTeacher("0002").expectStatus().isOk();
        deleteTeacher("0002").expectStatus().isOk();
    }

    private WebTestClient.ResponseSpec deleteTeacher(String nationalId) {
        return webTestClient
                .delete()
                .uri(TeacherResource.TEACHERS + TeacherResource.NATIONAL_ID, nationalId)
                .exchange();
    }
}
