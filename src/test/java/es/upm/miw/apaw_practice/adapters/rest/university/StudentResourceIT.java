package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.DegreePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.StudentPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class StudentResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private StudentPersistence studentPersistence;

    @Autowired
    private DegreePersistence degreePersistence;

    @Test
    void testUpdateNonExisting() {
        Student student = new Student("example@alumnos.upm.es", "Marco", "Valencia", LocalDate.of(1992, 12, 12), List.of());
        updateStudent(student).expectStatus().is4xxClientError();
    }

    @Test
    void testUpdateExisting() {
        Student student = studentPersistence.read("olivia.wilson@example.org");
        assertEquals("Olivia", student.getFirstName());
        assertEquals("Liverpool", student.getPlaceOfBirth());
        assertEquals(LocalDate.of(1999, 9, 12), student.getEnrollmentDate());
        assertEquals(List.of(degreePersistence.read(2000)), student.getDegrees());
        student.setFirstName("Martha");
        student.setPlaceOfBirth("Birmingham");
        student.setEnrollmentDate(LocalDate.of(2002, 12, 12));
        student.setDegrees(List.of(degreePersistence.read(2003)));
        updateStudent(student).expectStatus().isOk();
        Student updatedStudent = studentPersistence.read(student.getEmail());
        assertEquals(student.getFirstName(), updatedStudent.getFirstName());
        assertEquals(student.getPlaceOfBirth(), updatedStudent.getPlaceOfBirth());
        assertEquals(student.getEnrollmentDate(), updatedStudent.getEnrollmentDate());
        assertEquals(student.getDegrees(), updatedStudent.getDegrees());
    }

    private WebTestClient.ResponseSpec updateStudent(Student student) {
        return webTestClient
                .put()
                .uri(StudentResource.STUDENTS + StudentResource.EMAIL, student.getEmail())
                .body(BodyInserters.fromValue(student))
                .exchange();
    }
}
