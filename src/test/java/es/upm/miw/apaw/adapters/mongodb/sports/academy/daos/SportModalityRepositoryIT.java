package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class SportModalityRepositoryIT {

    @Autowired
    private ISportModalityRepository sportModalityRepository;

    @Test
    void testFindBySportId() {
        assertTrue(this.sportModalityRepository.findBySportId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006")).isPresent());
        var sportModality = this.sportModalityRepository.findBySportId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006")).get();
        assertThat(sportModality).isNotNull();
        assertThat(sportModality.getSportId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0006"));
        assertThat(sportModality.getTitle()).isEqualTo("Tennis");
        assertThat(sportModality.getLevel()).isZero();
        assertThat(sportModality.getTargetAudience()).isZero();
        assertThat(sportModality.isActive()).isFalse();
        assertThat(sportModality.getAthletes()).hasSize(2);
        assertThat(sportModality.getProfessorId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"));
    }
}
