package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class LocationRepositoryIT {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void testFindByPosition() {
        Optional<LocationEntity> optionalLocation = this.locationRepository.findByPosition("A1");
        assertThat(optionalLocation).isPresent();

        LocationEntity location = optionalLocation.get();
        assertThat(location.getPosition()).isEqualTo("A1");
        assertThat(location.getCurrentStock()).isNotNull();
        assertThat(location.getProductItemEntities()).isNotEmpty();

        assertThat(location.getProductItemEntities())
                .anySatisfy(item -> assertThat(item.getBarcode()).isNotBlank());
    }

}