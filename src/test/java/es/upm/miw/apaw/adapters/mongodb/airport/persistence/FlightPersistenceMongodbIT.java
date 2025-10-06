package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.airport.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
public class FlightPersistenceMongodbIT {

    @Autowired
    private FlightPersistenceMongodb flightPersistence;

    @Test
    void testReadOk() {
        Flight flight = this.flightPersistence.read(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"));
        assertThat(flight).isNotNull();
        assertThat(flight.getDepartureTime()).isEqualTo(LocalDateTime.of(2025,10, 5, 12, 0));
        assertThat(flight.getArrivalTime()).isEqualTo(LocalDateTime.of(2025,10, 5, 16, 0));
        assertThat(flight.getDestination()).isEqualTo("BCN");
        assertThat(flight.getBoardingGate().getNumber()).isEqualTo("A01");
        assertThat(flight.getPlane().getRegistrationNumber()).isEqualTo("EC-VAL");
        assertThat(flight.getPassengers().isEmpty()).isFalse();
        assertThat(flight.getPilot()).isNotNull();
    }

    @Test
    void testReadNotFound() {
        assertThatThrownBy(() -> this.flightPersistence.read(UUID.randomUUID()))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Flight id");
    }
}
