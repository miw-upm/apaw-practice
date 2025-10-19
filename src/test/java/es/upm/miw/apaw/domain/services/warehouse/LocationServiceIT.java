package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.Location;
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

    @Autowired
    private LocationService locationPersistence;

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
        assertThat(location.getAvailability()).isNotNull();
        assertThat(location.getProductItems()).hasSize(2);
    }

    @Test
    void testReadByPositionNotFound() {
        assertThrows(NotFoundException.class, () -> this.locationService.readByPosition("Z9"));
    }

    @Test
    void testUpdateAvailability() {
        String position = "A1";
    Boolean originalAvailability = this.locationPersistence.readByPosition(position).getAvailability();

    try {
        Location updated = this.locationService.updateAvailability(position, !originalAvailability);
        assertThat(updated.getAvailability()).isNotEqualTo(originalAvailability);
    } finally {
        this.locationService.updateAvailability(position, originalAvailability);
    }

    Location restored = this.locationPersistence.readByPosition(position);
    assertThat(restored.getAvailability()).isEqualTo(originalAvailability);
    }

}