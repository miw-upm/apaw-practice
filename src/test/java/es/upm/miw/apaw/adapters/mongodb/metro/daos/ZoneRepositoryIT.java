package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.ZoneEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ZoneRepositoryIT {

    @Autowired
    private ZoneRepository zoneRepository;

    @Test
    void testFindByType() {
        String type = "ZoneA";

        Optional<ZoneEntity> zone = zoneRepository.findByType(type);

        assertThat(zone).isPresent();
        assertThat(zone.get().getType()).isEqualTo(type);
        assertThat(zone.get().getTicketPrice()).isEqualTo(new BigDecimal("2.50"));
    }

    @Test
    void testFindByTypeNotFound() {
        String nonExistentType = "NonExistentType";
        Optional<ZoneEntity> zone = zoneRepository.findByType(nonExistentType);
        assertThat(zone).isEmpty();
    }

    @Test
    void testFindByTypeEmptyString() {
        String emptyType = "";
        Optional<ZoneEntity> zone = zoneRepository.findByType(emptyType);
        assertThat(zone).isEmpty();
    }
}