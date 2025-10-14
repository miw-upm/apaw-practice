package es.upm.miw.apaw.functionaltests.university;

import es.upm.miw.apaw.adapters.resources.university.TeacherResource;
import es.upm.miw.apaw.domain.models.university.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class TeacherResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");
        
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T001")
                .specialization("Updated Computer Science")
                .fullName("Updated Teacher Name")
                .tenured(false)
                .build();

        webTestClient.put()
                .uri(TeacherResource.TEACHERS + TeacherResource.ID_ID, teacherId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedTeacher)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Teacher.class)
                .value(teacher -> {
                    assertThat(teacher.getIdentificationCode()).isEqualTo("T001");
                    assertThat(teacher.getSpecialization()).isEqualTo("Updated Computer Science");
                    assertThat(teacher.getFullName()).isEqualTo("Updated Teacher Name");
                    assertThat(teacher.getTenured()).isFalse();
                });
    }

    @Test
    void testUpdateWithNewIdentificationCode() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");
        
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T999")
                .specialization("New Specialization")
                .fullName("New Teacher Name")
                .tenured(true)
                .build();

        webTestClient.put()
                .uri(TeacherResource.TEACHERS + TeacherResource.ID_ID, teacherId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedTeacher)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Teacher.class)
                .value(teacher -> {
                    assertThat(teacher.getIdentificationCode()).isEqualTo("T999");
                    assertThat(teacher.getSpecialization()).isEqualTo("New Specialization");
                    assertThat(teacher.getFullName()).isEqualTo("New Teacher Name");
                    assertThat(teacher.getTenured()).isTrue();
                });
    }

    @Test
    void testUpdateNotFound() {
        UUID nonExistentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");
        
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T999")
                .specialization("New Specialization")
                .fullName("New Teacher Name")
                .tenured(true)
                .build();

        webTestClient.put()
                .uri(TeacherResource.TEACHERS + TeacherResource.ID_ID, nonExistentId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedTeacher)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateWithConflictingIdentificationCode() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");
        
        // Try to update with an identification code that already exists (T002)
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("T002")
                .specialization("Updated Specialization")
                .fullName("Updated Teacher Name")
                .tenured(true)
                .build();

        webTestClient.put()
                .uri(TeacherResource.TEACHERS + TeacherResource.ID_ID, teacherId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedTeacher)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateBadRequest() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");
        
        // Try to update with empty identification code (should fail validation)
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode("")
                .specialization("Updated Specialization")
                .fullName("Updated Teacher Name")
                .tenured(true)
                .build();

        webTestClient.put()
                .uri(TeacherResource.TEACHERS + TeacherResource.ID_ID, teacherId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedTeacher)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateBadRequestNullIdentificationCode() {
        UUID teacherId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");
        
        // Try to update with null identification code (should fail validation)
        Teacher updatedTeacher = Teacher.builder()
                .identificationCode(null)
                .specialization("Updated Specialization")
                .fullName("Updated Teacher Name")
                .tenured(true)
                .build();

        webTestClient.put()
                .uri(TeacherResource.TEACHERS + TeacherResource.ID_ID, teacherId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedTeacher)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
