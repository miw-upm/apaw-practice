package es.upm.miw.apaw.domain.services.metro;

import es.upm.miw.apaw.adapters.mongodb.metro.daos.ZoneRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.metro.Zone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ZoneServiceIT {

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private ZoneRepository zoneRepository;

    @Test
    void testUpdate() {
        String zoneId = "ZoneA";

        Zone updatedZone = Zone.builder()
                .type("ZoneG")
                .ticketPrice(new BigDecimal("6.00"))
                .build();

        Zone result = this.zoneService.update(zoneId, updatedZone);

        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("ZoneG");
        assertThat(result.getTicketPrice()).isEqualTo(new BigDecimal("6.00"));
    }

    @Test
    void testUpdateWithNewType() {
        String zoneType = "ZoneB";

        Zone updatedZone = Zone.builder()
                .type("ZoneH")
                .ticketPrice(new BigDecimal("4.50"))
                .build();

        Zone result = this.zoneService.update(zoneType, updatedZone);

        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("ZoneH");
        assertThat(result.getTicketPrice()).isEqualTo(new BigDecimal("4.50"));
    }

    @Test
    void testUpdateWithConflictingType() {
        String zoneType = "ZoneC";

        Zone updatedZone = Zone.builder()
                .type("ZoneD")
                .ticketPrice(new BigDecimal("4.00"))
                .build();

        assertThatThrownBy(() -> this.zoneService.update(zoneType, updatedZone))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Type already exists: ZoneD");
    }

    @Test
    void testUpdateNotFound() {
        String nonExistentId = "ZoneNULL";

        Zone updatedZone = Zone.builder()
                .type("T009")
                .ticketPrice(new BigDecimal("2.50"))
                .build();

        assertThatThrownBy(() -> this.zoneService.update(nonExistentId, updatedZone))
                .isInstanceOf(es.upm.miw.apaw.domain.exceptions.NotFoundException.class);
    }
}