package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.domain.models.airport.Plane;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class PlanePersistenceMongodbIT {

    @Autowired
    private PlanePersistenceMongodb planePersistence;

    @Test
    void testCreate() {
        Plane plane = Plane.builder()
                .registrationNumber("EC-PMI")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.of(2024,1, 1, 12, 0))
                .manufacturer("Airbus")
                .build();

        Plane planeDb = this.planePersistence.create(plane);
        assertThat(planeDb.getRegistrationNumber()).isEqualTo(plane.getRegistrationNumber());
    }

    @Test
    void testExistRegistrationNumberPresent() {
        assertThat(this.planePersistence.existRegistrationNumber("EC-MAD")).isTrue();
    }

    @Test
    void testExistRegistrationNumberNotPresent() {
        assertThat(this.planePersistence.existRegistrationNumber("Test")).isFalse();
    }
}
