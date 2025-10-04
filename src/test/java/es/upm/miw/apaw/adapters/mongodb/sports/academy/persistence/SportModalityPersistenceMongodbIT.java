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

    @Test
    void testGetByIdNotFound() {
        assertThrows(NotFoundException.class, () -> this.sportModalityPersistence.getById(UUID.randomUUID()));
    }

    @Test
    void testCreateAndGetById() {
        var sportModality = SportModality.builder()
                .sportId(UUID.randomUUID())
                .title("Tennis")
                .level(Level.BEGINNER)
                .targetAudience(TargetAudience.KIDS)
                .professor(Professor.builder()
                        .user(UserDto.builder().id(UUID.randomUUID()).build())
                        .build())
                .athletes(new ArrayList<>())
                .build();
        this.sportModalityPersistence.create(sportModality);
        SportModality sportModalityBD = this.sportModalityPersistence.getById(sportModality.getSportId());
        assertThat(sportModalityBD.getSportId()).isEqualTo(sportModality.getSportId());
        assertThat(sportModalityBD.getTitle()).isEqualTo("Tennis");
        assertThat(sportModalityBD.getLevel()).isEqualTo(Level.BEGINNER);
        assertThat(sportModalityBD.getTargetAudience()).isEqualTo(TargetAudience.KIDS);
        assertThat(sportModalityBD.getProfessor().getUser().getId()).isEqualTo(sportModality.getProfessor().getUser().getId());
        assertTrue(sportModalityBD.getAthletes().isEmpty());
    }

    @Test
    void testCreateAndUpdate() {
        var sportModality = SportModality.builder()
                .sportId(UUID.randomUUID())
                .title("Tennis")
                .level(Level.BEGINNER)
                .targetAudience(TargetAudience.KIDS)
                .professor(Professor.builder()
                        .user(UserDto.builder().id(UUID.randomUUID()).build())
                        .build())
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
        var sportModality = SportModality.builder()
                .sportId(UUID.randomUUID())
                .title("Tennis")
                .level(Level.BEGINNER)
                .targetAudience(TargetAudience.KIDS)
                .professor(Professor.builder()
                        .user(UserDto.builder().id(UUID.randomUUID()).build())
                        .build())
                .athletes(new ArrayList<>())
                .build();
        assertThrows(NotFoundException.class, () -> this.sportModalityPersistence.update(sportModality.getSportId(), sportModality));
    }
}
