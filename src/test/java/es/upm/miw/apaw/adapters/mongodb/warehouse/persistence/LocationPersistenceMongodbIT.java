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
        assertThat(locations.get(0).getPosition()).startsWith("Z");
    }

    @Test
    void testUpdate() {
        UUID id = UUID.fromString("bbbb1111-2222-3333-4444-555566660001");
        Location location = new Location(
                999,
                "Z1-A",
                LocalDateTime.now(),
                true                  // availability
        );

        Location updated = this.locationPersistence.update(id, location);
        assertThat(updated.getCurrentStock()).isEqualTo(999);
    }

    @Test
    void testUpdateNotFound() {
        UUID randomId = UUID.randomUUID();
        Location location = new Location(
                20,
                "Z1-X",
                LocalDateTime.now(),
                false
        );

        assertThrows(RuntimeException.class, () ->
                this.locationPersistence.update(randomId, location)
        );
    }

}
