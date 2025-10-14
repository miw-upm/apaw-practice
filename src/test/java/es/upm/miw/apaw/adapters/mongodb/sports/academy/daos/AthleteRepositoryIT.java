package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;


import es.upm.miw.apaw.domain.models.sports.academy.enums.Gender;
import es.upm.miw.apaw.BaseSportsAcademyTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class AthleteRepositoryIT extends BaseSportsAcademyTests {

    @Autowired
    private AthleteRepository athleteRepository;

    @Test
    void testFindByUserDtoId() {
        assertTrue(this.athleteRepository.findByUserDtoId(athletes[0].getUserDtoId()).isPresent());
        var athlete = this.athleteRepository.findByUserDtoId(athletes[0].getUserDtoId()).get();
        assertThat(athlete).isNotNull();
        assertThat(athlete.getUserDtoId()).isEqualTo(athletes[0].getUserDtoId());
        assertThat(athlete.getGender()).isEqualTo(Gender.MALE.getValue());
        assertThat(athlete.getHeight()).isEqualTo(1.78);
        assertThat(athlete.getWeight()).isEqualTo(72.0);
        assertThat(athlete.getBirthDate()).isEqualTo(LocalDate.of(2000, 6, 20));
        assertThat(athlete.getLegalGuardians()).hasSize(1);
        assertThat(athlete.getLegalGuardians().getFirst().getUserDtoId()).isEqualTo(athletes[0].getLegalGuardians().getFirst().getUserDtoId());
        assertThat(athlete.getSportModalities()).hasSize(2);
    }

    @Test
    void testFindByLegalGuardiansIn(){
        var legalGuardian = athletes[0].getLegalGuardians().getFirst();
        assertThat(this.athleteRepository.findByLegalGuardiansIn(Collections.singletonList(legalGuardian)))
                .hasSize(1);
        var athlete = this.athleteRepository.findByLegalGuardiansIn(Collections.singletonList(legalGuardian)).getFirst();
        assertThat(athlete).isNotNull();
        assertThat(athlete.getUserDtoId()).isEqualTo(athletes[0].getUserDtoId());
        assertThat(athlete.getGender()).isEqualTo(athletes[0].getGender());
        assertThat(athlete.getHeight()).isEqualTo(athletes[0].getHeight());
        assertThat(athlete.getWeight()).isEqualTo(athletes[0].getWeight());
        assertThat(athlete.getBirthDate()).isEqualTo(athletes[0].getBirthDate());
        assertThat(athlete.getLegalGuardians()).hasSize(1);
        assertThat(athlete.getLegalGuardians().getFirst()).isEqualTo(athletes[0].getLegalGuardians().getFirst());
        assertThat(athlete.getSportModalities()).hasSize(2);
        assertThat(athlete.getSportModalities().getFirst()).isEqualTo(athletes[0].getSportModalities().getFirst());
        assertThat(athlete.getSportModalities().getLast()).isEqualTo(athletes[0].getSportModalities().getLast());
    }
}
