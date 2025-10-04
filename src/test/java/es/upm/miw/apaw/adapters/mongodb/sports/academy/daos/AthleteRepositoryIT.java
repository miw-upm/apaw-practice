package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;


import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class AthleteRepositoryIT {

    @Autowired
    private AthleteRepository athleteRepository;

    @Test
    void testFindByUserDtoId() {
        assertTrue(this.athleteRepository.findByUserDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")).isPresent());
        var athlete = this.athleteRepository.findByUserDtoId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")).get();
        assertThat(athlete).isNotNull();
        assertThat(athlete.getUserDtoId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"));
        assertThat(athlete.getGender()).isEqualTo(Gender.MALE.getValue());
        assertThat(athlete.getHeight()).isEqualTo(1.78);
        assertThat(athlete.getWeight()).isEqualTo(72.0);
        assertThat(athlete.getBirthDate()).isEqualTo(LocalDate.of(2000, 6, 20));
        assertThat(athlete.getLegalGuardians()).hasSize(1);
        assertThat(athlete.getLegalGuardians().getFirst().getUserDtoId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"));
        assertThat(athlete.getSportModalities()).isEmpty();
    }
}
