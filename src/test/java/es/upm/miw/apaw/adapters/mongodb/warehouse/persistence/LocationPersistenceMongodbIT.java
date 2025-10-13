package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class LocationPersistenceMongodbIT {

    @Autowired
    private LocationPersistenceMongodb locationPersistence;

    @Test
    void testReadAll() {
        List<Location> locations = this.locationPersistence.readAll().toList();
        assertThat(locations).isNotEmpty();
        assertThat(locations)
                .extracting(Location::getPosition)
                .contains("A-01-01", "B-02-03");
    }

    @Test
    void testReadByPosition() {
        Location location = this.locationPersistence.readByPosition("A-01-01");
        assertThat(location.getPosition()).isEqualTo("A-01-01");
        assertThat(location.getAvailability()).isTrue();
        assertThat(location.getProductItems()).hasSize(2);
    }

}
