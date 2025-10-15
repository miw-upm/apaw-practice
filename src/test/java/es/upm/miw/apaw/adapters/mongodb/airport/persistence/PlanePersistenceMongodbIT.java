package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.airport.Plane;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void testCreateAndUpdate() {
        Plane plane = Plane.builder()
                .registrationNumber("ED-PMI")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.of(2024,1, 1, 12, 0))
                .manufacturer("Airbus")
                .build();
        Plane planeDb = this.planePersistence.create(plane);
        planeDb.setSeatCount(190);
        this.planePersistence.update("ED-PMI", planeDb);
        planeDb = this.planePersistence.findByRegistrationNumber("ED-PMI");
        assertThat(planeDb.getSeatCount()).isEqualTo(190);
    }

    @Test
    void testUpdateRegistrationNumberNotFound() {
        Plane planeDb = this.planePersistence.findByRegistrationNumber("EC-MAD");
        assertThatThrownBy(() -> this.planePersistence.update("Test", planeDb))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Plane registration number");
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
