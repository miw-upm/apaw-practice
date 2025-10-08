package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.persistenceports.airport.PlanePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class PlaneServiceIT {

    @Autowired
    private PlaneService planeService;

    @Autowired
    private PlanePersistence planePersistence;

    @Test
    void testCreatePlane() {
        Plane plane = Plane.builder()
                .registrationNumber("TestIT0")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.now().minusMonths(3))
                .manufacturer("Airbus")
                .build();

        this.planeService.create(plane);

        assertThat(this.planePersistence.existRegistrationNumber(plane.getRegistrationNumber())).isTrue();
    }

    @Test
    void testCreateRegistrationNumberConflict() {
        Plane plane = Plane.builder()
                .registrationNumber("TestIT1")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.now().minusMonths(3))
                .manufacturer("Airbus")
                .build();

        this.planeService.create(plane);
        assertThatThrownBy(() -> this.planeService.create(plane))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Test");

        assertThat(this.planePersistence.existRegistrationNumber(plane.getRegistrationNumber())).isTrue();
    }
}
