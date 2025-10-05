package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Professor;
import es.upm.miw.apaw.domain.models.sports.academy.SportModality;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Level;
import es.upm.miw.apaw.domain.models.sports.academy.enums.TargetAudience;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SportModalityPersistenceMongodbIT {

    @Autowired
    private SportModalityPersistenceMongodb sportModalityPersistence;

    @Autowired
    private ProfessorPersistenceMongodb professorPersistence;


    @Test
    void testGetByIdNotFound() {
        var id = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> this.sportModalityPersistence.getById(id));
    }

    @Test
    void testCreateAndGetById() {
        var professor = this.professorPersistence.getById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"));
        var sportModality = SportModality.builder()
                .sportId(UUID.randomUUID())
                .title("Tennis")
                .level(Level.BEGINNER)
                .targetAudience(TargetAudience.KIDS)
                .professor(professor)
                .athletes(new ArrayList<>())
                .build();
        SportModality sportModalityBD = this.sportModalityPersistence.create(sportModality);
        assertThat(sportModalityBD.getSportId()).isEqualTo(sportModality.getSportId());
        assertThat(sportModalityBD.getTitle()).isEqualTo("Tennis");
        assertThat(sportModalityBD.getLevel()).isEqualTo(Level.BEGINNER);
        assertThat(sportModalityBD.getTargetAudience()).isEqualTo(TargetAudience.KIDS);
        assertThat(sportModalityBD.getProfessor().getUser().getId()).isEqualTo(sportModality.getProfessor().getUser().getId());
        assertThat(sportModalityBD.getProfessor().getSpecialization()).isEqualTo(sportModality.getProfessor().getSpecialization());
        assertThat(sportModalityBD.getProfessor().getLicenseNumber()).isEqualTo(sportModality.getProfessor().getLicenseNumber());
        assertTrue(sportModalityBD.getAthletes().isEmpty());
    }

    @Test
    void testCreateAndUpdate() {
        var professor = this.professorPersistence.getById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"));
        var sportModality = SportModality.builder()
                .sportId(UUID.randomUUID())
                .title("Tennis")
                .level(Level.BEGINNER)
                .targetAudience(TargetAudience.KIDS)
                .professor(professor)
                .athletes(new ArrayList<>())
                .build();
        SportModality sportModalityBD = this.sportModalityPersistence.create(sportModality);
        sportModalityBD.setTitle("Karate");
        sportModalityBD = this.sportModalityPersistence.update(sportModality.getSportId(), sportModalityBD);
        assertThat(sportModalityBD.getTitle()).isEqualTo("Karate");
        assertThat(sportModalityBD.getSportId()).isEqualTo(sportModality.getSportId());
        assertThat(sportModalityBD.getLevel()).isEqualTo(Level.BEGINNER);
        assertThat(sportModalityBD.getTargetAudience()).isEqualTo(TargetAudience.KIDS);
        assertThat(sportModalityBD.getProfessor().getUser().getId()).isEqualTo(sportModality.getProfessor().getUser().getId());
        assertThat(sportModalityBD.getProfessor().getSpecialization()).isEqualTo(sportModality.getProfessor().getSpecialization());
        assertThat(sportModalityBD.getProfessor().getLicenseNumber()).isEqualTo(sportModality.getProfessor().getLicenseNumber());
        assertTrue(sportModalityBD.getAthletes().isEmpty());
    }

    @Test
    void testGetAll() {
        int page = 1;
        int pageSize = 10;
        var modalities = this.sportModalityPersistence.getAll(page, pageSize).toList();
        assertFalse(modalities.isEmpty());
    }

    @Test
    void testGetAllPageOutOfRange() {
        int page = 100;
        int pageSize = 10;
        var sportModalities = this.sportModalityPersistence.getAll(page, pageSize).toList();
        assertTrue(sportModalities.isEmpty());
    }

    @Test
    void testGetAllPageNegative() {
        int page = -1;
        int pageSize = 10;
        var sportModalities = this.sportModalityPersistence.getAll(page, pageSize).toList();
        assertFalse(sportModalities.isEmpty());
    }

    @Test
    void testUpdateNotFound() {
        var id = UUID.randomUUID();
        var sportModality = SportModality.builder()
                .sportId(id)
                .title("Tennis")
                .level(Level.BEGINNER)
                .targetAudience(TargetAudience.KIDS)
                .professor(Professor.builder()
                        .user(UserDto.builder()
                                .id(UUID.randomUUID())
                                .firstName("John")
                                .mobile("+34711036811")
                                .build())
                        .licenseNumber("ABC123")
                        .specialization("Tennis")
                        .build())
                .athletes(new ArrayList<>())
                .build();
        assertThrows(NotFoundException.class, () -> this.sportModalityPersistence.update(id, sportModality));
    }
}
