package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.persistenceports.warehouse.LocationPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class LocationServiceIT {

    @Autowired
    private LocationService locationService;

    @Test
    void testReadAll() {
        List<Location> locations = this.locationService.readAll().toList();
        assertThat(locations).isNotEmpty();
        assertThat(locations.getFirst().getPosition()).isNotBlank();
    }

    @Test
    void testReadByPositionExisting() {
        Location location = this.locationService.readByPosition("A1");
        assertThat(location.getPosition()).isEqualTo("A1");
        assertThat(location.getCurrentStock()).isEqualTo(100);
        assertThat(location.getAvailability()).isTrue();
        assertThat(location.getProductItems()).hasSize(2);
    }

    @Test
    void testReadByPositionNotFound() {
        assertThrows(NotFoundException.class, () -> this.locationService.readByPosition("Z9"));
    }

}