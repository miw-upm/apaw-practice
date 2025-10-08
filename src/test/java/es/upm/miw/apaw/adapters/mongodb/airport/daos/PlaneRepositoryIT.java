package es.upm.miw.apaw.adapters.mongodb.airport.daos;

import es.upm.miw.apaw.adapters.mongodb.airport.entities.PlaneEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class PlaneRepositoryIT {

    @Autowired
    private PlaneRepository planeRepository;

    @Test
    void testFindByRegistrationNumber() {
        assertTrue(this.planeRepository.findByRegistrationNumber("EC-MAD").isPresent());
        PlaneEntity plane = this.planeRepository.findByRegistrationNumber("EC-MAD").get();
        assertThat(plane.getModel()).isEqualTo("A320neo");
        assertThat(plane.getSeatCount()).isEqualTo(186);
        assertThat(plane.getCreatedAt()).isEqualTo(LocalDateTime.of(2024,1, 1, 12, 0));
        assertThat(plane.getManufacturer()).isEqualTo("Airbus");
    }
}
