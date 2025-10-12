package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class LocationRepositoryIT {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void testFindAll() {
        List<LocationEntity> locations = this.locationRepository.findAll();
        assertThat(locations).isNotEmpty();
    }

    @Test
    void testFindByPosition() {
        Optional<LocationEntity> optionalLocation = this.locationRepository.findByPosition("Z1-A");
        assertTrue(optionalLocation.isPresent());
        LocationEntity location = optionalLocation.get();
        assertThat(location.getPosition()).isEqualTo("Z1-A");
        assertThat(location.getCurrentStock()).isGreaterThanOrEqualTo(150);
    }

    @Test
    void testFindByAvailabilityTrue() {
        List<LocationEntity> availableLocations = this.locationRepository.findByAvailabilityTrue();
        assertThat(availableLocations)
                .isNotEmpty()
                .allMatch(LocationEntity::getAvailability);
    }

}
