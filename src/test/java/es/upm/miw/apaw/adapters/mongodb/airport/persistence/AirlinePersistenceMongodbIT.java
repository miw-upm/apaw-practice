package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class AirlinePersistenceMongodbIT {

    @Autowired
    private AirlinePersistenceMongodb airlinePersistence;

    @Test
    void testDeleteByName() {
        assertThat(this.airlinePersistence.existsName("Vueling")).isTrue();
        this.airlinePersistence.delete("Vueling");
        assertThat(this.airlinePersistence.existsName("Vueling")).isFalse();
    }

    @Test
    void testExistsName() {
        assertThat(this.airlinePersistence.existsName("UPM Airlines")).isTrue();
    }
}
