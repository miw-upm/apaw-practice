package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.persistenceports.airport.AirlinePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AirlineServiceIT {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private AirlinePersistence airlinePersistence;

    @Test
    void testDelete() {
        assertThat(this.airlinePersistence.existsName("UPM Dellines Plus")).isTrue();
        this.airlineService.delete("UPM Dellines Plus");
        assertThat(this.airlinePersistence.existsName("UPM Dellines Plus")).isFalse();
    }

    @Test
    void testReadByPlaneModel() {
        assertThat(this.airlineService.readByPlaneModel("B787-9 Dreamliner"))
                .isNotNull()
                .containsExactlyInAnyOrder("UPM Airlines", "Vueling");
    }
}
