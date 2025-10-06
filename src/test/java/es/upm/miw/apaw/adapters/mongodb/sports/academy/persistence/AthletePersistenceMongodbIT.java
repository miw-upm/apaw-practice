package es.upm.miw.apaw.adapters.mongodb.sports.academy.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.BaseSportsAcademyIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AthletePersistenceMongodbIT extends BaseSportsAcademyIT {

    @Autowired
    private AthletePersistenceMongodb athletePersistenceMongodb;

    @Test
    void testGetByIdNotFound() {
        var id = UUID.randomUUID();
        assertThrows(NotFoundException.class, () -> this.athletePersistenceMongodb.getById(id));
    }

    @Test
    void testCreateAndGetById() {
        Athlete athlete =  Athlete.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .gender(Gender.MALE)
                .height(1.78)
                .weight(72)
                .birthDate(LocalDate.of(2000, 6, 20))
                .legalGuardians(new ArrayList<>())
                .sportModalities(new ArrayList<>())
                .build();
        this.athletePersistenceMongodb.create(athlete);
        Athlete athleteBD = this.athletePersistenceMongodb.getById(athlete.getUser().getId());
        assertThat(athleteBD.getUser().getId()).isEqualTo(athlete.getUser().getId());
        assertThat(athleteBD.getGender()).isEqualTo(athlete.getGender());
        assertThat(athleteBD.getHeight()).isEqualTo(athlete.getHeight());
        assertThat(athleteBD.getWeight()).isEqualTo(athlete.getWeight());
        assertThat(athleteBD.getBirthDate()).isEqualTo(athlete.getBirthDate());
        assertThat(athleteBD.getLegalGuardians()).isEmpty();
        assertThat(athleteBD.getSportModalities()).isEmpty();
    }

    @Test
    void testCreateAndUpdate() {
        Athlete athlete = Athlete.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .gender(Gender.MALE)
                .height(1.78)
                .weight(72)
                .birthDate(LocalDate.of(2000, 6, 20))
                .legalGuardians(new ArrayList<>())
                .sportModalities(new ArrayList<>())
                .build();
        Athlete athleteBD = this.athletePersistenceMongodb.create(athlete);
        athleteBD.setGender(Gender.FEMALE);
        this.athletePersistenceMongodb.update(athlete.getUser().getId(), athleteBD);
        athleteBD = this.athletePersistenceMongodb.getById(athlete.getUser().getId());
        assertThat(athleteBD.getGender()).isEqualTo(Gender.FEMALE);
    }

    @Test
    void testGetAll() {
        int page = 1;
        int pageSize = 10;
        var athletes = this.athletePersistenceMongodb.getAll(page, pageSize).toList();
        assertFalse(athletes.isEmpty());
    }

    @Test
    void testGetAllPageOutOfRange() {
        int page = 100;
        int pageSize = 10;
        var athletes = this.athletePersistenceMongodb.getAll(page, pageSize).toList();
        assertTrue(athletes.isEmpty());
    }

    @Test
    void testGetAllPageNegative() {
        int page = -1;
        int pageSize = 10;
        var athletes = this.athletePersistenceMongodb.getAll(page, pageSize).toList();
        assertFalse(athletes.isEmpty());
    }

    @Test
    void testUpdateNotFound() {
        var id = UUID.randomUUID();
        Athlete athlete = Athlete.builder()
                .user(UserDto.builder().id(id).build())
                .gender(Gender.MALE)
                .height(1.78)
                .weight(72)
                .birthDate(LocalDate.of(2000, 6, 20))
                .legalGuardians(new ArrayList<>())
                .sportModalities(new ArrayList<>())
                .build();
        assertThrows(NotFoundException.class, () -> this.athletePersistenceMongodb.update(id, athlete));
    }
}
