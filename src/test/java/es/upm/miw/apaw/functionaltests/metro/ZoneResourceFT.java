package es.upm.miw.apaw.functionaltests.metro;
import es.upm.miw.apaw.adapters.resources.metro.ZoneResource;
import es.upm.miw.apaw.domain.models.metro.Zone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ZoneResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        String zoneType = "ZoneA";

        Zone updatedZone = Zone.builder()
                .type("ZoneA")
                .ticketPrice(new BigDecimal("4.00"))
                .build();

        webTestClient.put()
                .uri(ZoneResource.ZONES + ZoneResource.TYPE, zoneType)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedZone)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Zone.class)
                .value(zone -> {
                    assertThat(zone.getType()).isEqualTo("ZoneA");
                    assertThat(zone.getTicketPrice()).isEqualTo(new BigDecimal("4.00"));
                });
    }

    @Test
    void testUpdateWithNewType() {
        String zoneType = "ZoneA";

        Zone updatedZone = Zone.builder()
                .type("ZoneG")
                .ticketPrice(new BigDecimal("4.00"))
                .build();

        webTestClient.put()
                .uri(ZoneResource.ZONES + ZoneResource.TYPE, zoneType)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedZone)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Zone.class)
                .value(zone -> {
                    assertThat(zone.getType()).isEqualTo("ZoneG");
                    assertThat(zone.getTicketPrice()).isEqualTo(new BigDecimal("4.00"));
                });
    }

    @Test
    void testUpdateNotFound() {
        String nonExistentType = "ZoneNULL";

        Zone updatedZone = Zone.builder()
                .type("ZoneA")
                .ticketPrice(new BigDecimal("4.00"))
                .build();

        webTestClient.put()
                .uri(ZoneResource.ZONES + ZoneResource.TYPE, nonExistentType)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedZone)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateWithConflictingType() {
        String zoneType = "ZoneA";

        Zone updatedZone = Zone.builder()
                .type("ZoneB") // another zone such as Zone B already exists in the seeder.
                .ticketPrice(new BigDecimal("4.00"))
                .build();


        webTestClient.put()
                .uri(ZoneResource.ZONES + ZoneResource.TYPE, zoneType)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedZone)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }


    @Test
    void testUpdateBadRequestNullType() {
        String zoneType = "ZoneA";

        Zone updatedZone = Zone.builder()
                .type(null)
                .ticketPrice(null)
                .build();

        webTestClient.put()
                .uri(ZoneResource.ZONES + ZoneResource.TYPE, zoneType)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedZone)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
