package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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
        assertTrue(this.locationRepository.findByPosition("A-01-01").isPresent());
        LocationEntity location = this.locationRepository.findByPosition("A-01-01").get();
        assertThat(location.getCurrentStock()).isEqualTo(100);
        assertThat(location.getAvailability()).isTrue();
        assertThat(location.getProductItemEntities()).hasSize(2);
    }

    @Test
    void testFindByAvailabilityTrue() {
        List<LocationEntity> availableLocations = this.locationRepository.findByAvailabilityTrue();
        assertThat(availableLocations).isNotEmpty();
        assertThat(availableLocations)
                .extracting(LocationEntity::getPosition)
                .contains("A-01-01", "B-02-03");
    }

}
