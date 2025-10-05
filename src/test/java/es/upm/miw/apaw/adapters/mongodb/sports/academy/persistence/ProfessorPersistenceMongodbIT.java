package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProfessorPersistenceMongodbIT {

    @Autowired
    private ProfessorPersistenceMongodb professorPersistence;

    @Test
    void testGetByAllNotFound() {
        var id = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> this.professorPersistence.getById(id));
    }

    @Test
    void testCreateAndGetById() {
        Professor professor = Professor.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .specialization("Padel")
                .licenseNumber("XYZ789")
                .build();
        this.professorPersistence.create(professor);
        Professor professorBD = this.professorPersistence.getById(professor.getUser().getId());
        assertThat(professorBD.getUser().getId()).isEqualTo(professor.getUser().getId());
        assertThat(professorBD.getSpecialization()).isEqualTo("Padel");
        assertThat(professorBD.getLicenseNumber()).isEqualTo("XYZ789");
    }

    @Test
    void testCreateAndUpdate() {
        Professor professor = Professor.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .specialization("Basket")
                .licenseNumber("LMN456")
                .build();
        Professor professorBD = this.professorPersistence.create(professor);
        professorBD.setSpecialization("Volley");
        this.professorPersistence.update(professor.getUser().getId(), professorBD);
        professorBD = this.professorPersistence.getById(professor.getUser().getId());
        assertThat(professorBD.getSpecialization()).isEqualTo("Volley");
    }

    @Test
    void testGetAll() {
        int page = 1;
        int pageSize = 10;
        var professors = this.professorPersistence.getAll(page, pageSize).toList();
        assertFalse(professors.isEmpty());
    }

    @Test
    void testGetAllPageOutOfRange() {
        int page = 100;
        int pageSize = 10;
        var professors = this.professorPersistence.getAll(page, pageSize).toList();
        assertTrue(professors.isEmpty());
    }

    @Test
    void testGetAllPageNegative() {
        int page = -1;
        int pageSize = 10;
        var professors = this.professorPersistence.getAll(page, pageSize).toList();
        assertFalse(professors.isEmpty());
    }

    @Test
    void testUpdateNotFound() {
        var id = UUID.randomUUID();
        Professor professor = Professor.builder()
                .user(UserDto.builder().id(id).build())
                .specialization("Tennis")
                .licenseNumber("ABC123")
                .build();
        assertThrows(NotFoundException.class, () -> this.professorPersistence.update(id, professor));
    }
}
