package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.persistenceports.airport.AirlinePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class AirlineServiceIT {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirlinePersistence airlinePersistence;

    @Test
    void testDelete() {
        assertThat(this.airlinePersistence.existsName("Iberia Express")).isTrue();
        this.airlineService.delete("Iberia Express");
        assertThat(this.airlinePersistence.existsName("Iberia Express")).isFalse();
    }
}
