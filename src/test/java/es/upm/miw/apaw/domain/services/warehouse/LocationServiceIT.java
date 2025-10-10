package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.persistenceports.warehouse.LocationPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class LocationServiceIT {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationPersistence locationPersistence;

    @Test
    void testReadAll() {
        List<Location> locations = this.locationService.readAll()
                .collect(Collectors.toList());

        assertThat(locations).isNotNull();
        assertThat(locations).isNotEmpty();

        assertThat(locations).hasSize(3);

        assertThat(locations)
                .extracting(Location::getPosition)
                .containsExactlyInAnyOrder("Z1-A", "Z2-C", "Y1-F");
    }

}
