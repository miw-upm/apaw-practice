package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class LocationPersistenceMongodbIT {

    @Autowired
    private LocationPersistenceMongodb locationPersistence;

    @Test
    void testReadAll() {
        Stream<Location> locations = this.locationPersistence.readAll();
        assertThat(locations).isNotEmpty();
    }

    @Test
    void testReadByPosition() {
        Location location = this.locationPersistence.readByPosition("A1");
        assertThat(location.getAvailability()).isNotNull();
        assertThat(location.getProductItems()).hasSize(2);
    }

    @Test
    void testReadByPositionNotFound() {
        assertThrows(NotFoundException.class, () -> this.locationPersistence.readByPosition("Z9"));
    }

    @Test
    void testUpdateAvailability() {
        Location location = this.locationPersistence.readByPosition("A1");
        assertThat(location.getAvailability()).isNotNull();

        Boolean originalAvailability = location.getAvailability();

        location.setAvailability(!originalAvailability);
        Location updated = this.locationPersistence.update(location);

        assertThat(updated.getAvailability())
                .isNotNull()
                .isNotEqualTo(originalAvailability);
    }

}