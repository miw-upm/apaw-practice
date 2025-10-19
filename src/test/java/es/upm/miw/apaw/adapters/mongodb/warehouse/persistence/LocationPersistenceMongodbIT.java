package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.LocationRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.Location;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

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
        String position = "A1";
        Location original = this.locationPersistence.readByPosition(position);
        Boolean oldAvailability = original.getAvailability();

        try {

            original.setAvailability(!oldAvailability);
            this.locationPersistence.update(original);

            Location updated = this.locationPersistence.readByPosition(position);
            assertThat(updated.getAvailability()).isNotEqualTo(oldAvailability);
        } finally {

            original.setAvailability(oldAvailability);
            this.locationPersistence.update(original);
        }
    }

    @Test
    void testDeleteByPosition() {
        LocationRepository mockRepository = Mockito.mock(LocationRepository.class);

        LocationPersistenceMongodb localPersistence = new LocationPersistenceMongodb(mockRepository);

        String position = "B1";
        LocationEntity entity = LocationEntity.builder()
                .id(UUID.randomUUID())
                .position(position)
                .availability(true)
                .currentStock(10)
                .lastUpdateDate(LocalDateTime.now())
                .productItemEntities(List.of())
                .build();

        BDDMockito.given(mockRepository.findByPosition(position))
                .willReturn(Optional.of(entity));

        localPersistence.deleteByPosition(position);

        Mockito.verify(mockRepository).delete(entity);
    }

}