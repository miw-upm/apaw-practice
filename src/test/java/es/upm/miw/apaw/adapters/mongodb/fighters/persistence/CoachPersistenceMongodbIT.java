package es.upm.miw.apaw.adapters.mongodb.fighters.persistence;

import es.upm.miw.apaw.domain.models.fighters.Coach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CoachPersistenceMongodbIT {

    @Autowired
    private CoachPersistenceMongodb coachPersistence;

    @Test
    void testReadByFullName() {
        Coach coach = this.coachPersistence.readByFullName("John Smith");
        assertThat(coach.getFullName()).isEqualTo("John Smith");
        assertThat(coach.getAcademy()).isEqualTo("Nova Gym");
        assertThat(coach.getExperienceYears()).isEqualTo(12);
    }
}
