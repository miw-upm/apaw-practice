package es.upm.miw.apaw.adapters.mongodb.metro.persistence;

import es.upm.miw.apaw.adapters.mongodb.metro.daos.MetroSeeder;
import es.upm.miw.apaw.adapters.mongodb.metro.daos.ZoneRepository;
import es.upm.miw.apaw.adapters.mongodb.metro.entities.ZoneEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.metro.Zone;
import es.upm.miw.apaw.domain.persistenceports.metro.ZonePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ZonePersistenceMongodbIT {

    @Autowired
    private ZonePersistence zonePersistence;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private MetroSeeder metroSeeder;

    @BeforeEach
    void resetDb() {
        metroSeeder.deleteAll();
        metroSeeder.seedDatabase();
    }

    @Test
    void testGetById() {
        String zoneType = "ZoneA";

        Zone zone = zonePersistence.getByType(zoneType);

        assertThat(zone).isNotNull();
        assertThat(zone.getType()).isEqualTo("ZoneA");
        assertThat(zone.getTicketPrice()).isEqualTo(new BigDecimal("2.50"));
    }

    @Test
    void testGetByIdNotFound() {
        String nonExistentType = "ZoneNULL";

        assertThatThrownBy(() -> zonePersistence.getByType(nonExistentType))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Zone not found with type: " + nonExistentType);
    }

    @Test
    void testUpdate() {
        String zoneType = "ZoneA";

        Zone updatedZone = Zone.builder()
                .type("ZoneA")
                .ticketPrice(new BigDecimal("7.00"))
                .build();

        Zone result = zonePersistence.update(zoneType, updatedZone);

        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("ZoneA");
        assertThat(result.getTicketPrice()).isEqualTo(new BigDecimal("7.00"));

        Optional<ZoneEntity> zoneEntity = zoneRepository.findByType(zoneType);
        assertThat(zoneEntity).isPresent();
        assertThat(zoneEntity.get().getTicketPrice()).isEqualTo(new BigDecimal("7.00"));
    }

    @Test
    void testExistTypeTrue() {
        boolean exists = zonePersistence.existType("ZoneA");
        assertThat(exists).isTrue();
    }

    @Test
    void testExistTypeFalse() {
        boolean exists = zonePersistence.existType("ZoneNULL");
        assertThat(exists).isFalse();
    }

}
