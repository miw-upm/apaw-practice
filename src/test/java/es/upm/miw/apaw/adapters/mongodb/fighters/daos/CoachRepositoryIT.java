package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.CoachEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class CoachRepositoryIT {

    @Autowired
    private CoachRepository coachRepository;

    @Test
    void testReadByFullName() {
        assertTrue(this.coachRepository.findByFullName("John Smith").isPresent());
        CoachEntity coach = this.coachRepository.findByFullName("John Smith").get();
        assertThat(coach.getFullName()).isEqualTo("John Smith");
        assertThat(coach.getAcademy()).isEqualTo("Nova Gym");
        assertThat(coach.getExperienceYears()).isEqualTo(12);

    }

    @Test
    void testReadByFullName_notFound() {
        assertThat(this.coachRepository.findByFullName("no existe")).isEmpty();
    }
}
