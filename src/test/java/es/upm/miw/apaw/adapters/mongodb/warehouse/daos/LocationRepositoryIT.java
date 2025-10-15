package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class LocationRepositoryIT {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void testFindByPosition() {
        assertTrue(this.locationRepository.findByPosition("A1").isPresent());
        LocationEntity location = this.locationRepository.findByPosition("A1").get();
        assertThat(location.getCurrentStock()).isEqualTo(100);
        assertThat(location.getAvailability()).isTrue();
        assertThat(location.getProductItemEntities())
                .extracting(ProductItemEntity::getBarcode)
                .containsExactlyInAnyOrder("PI-001", "PI-002");
    }

}