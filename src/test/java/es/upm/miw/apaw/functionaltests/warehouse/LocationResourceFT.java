package es.upm.miw.apaw.functionaltests.warehouse;

import es.upm.miw.apaw.adapters.resources.warehouse.LocationResource;
import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.services.warehouse.LocationService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class LocationResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadAll() {
        this.webTestClient.get()
                .uri(LocationResource.LOCATIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Location.class)
                .value(list -> assertThat(list).isNotEmpty());
    }

    @Test
    void testReadByPositionExisting() {
        this.webTestClient.get()
                .uri(LocationResource.LOCATIONS + LocationResource.POSITION, "A1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Location.class)
                .value(loc -> assertThat(loc.getPosition()).isEqualTo("A1"));
    }

    @Test
    void testReadByPositionNotFound() {
        this.webTestClient.get()
                .uri(LocationResource.LOCATIONS + LocationResource.POSITION, "Z9")
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    void testUpdateAvailabilityExisting() {

        LocationService mockService = Mockito.mock(LocationService.class);

        WebTestClient localClient = WebTestClient.bindToController(
                new LocationResource(mockService)
        ).build();

        String position = "A1";
        Boolean newAvailability = false;
        Location mockUpdated = Location.builder()
                .position(position)
                .availability(newAvailability)
                .build();

        BDDMockito.given(mockService.updateAvailability(position, newAvailability))
                .willReturn(mockUpdated);

        localClient.patch()
                .uri(LocationResource.LOCATIONS + LocationResource.AVAILABILITY, position)
                .bodyValue(Map.of("availability", newAvailability))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Location.class)
                .value(loc -> {
                    assertThat(loc.getPosition()).isEqualTo("A1");
                    assertThat(loc.getAvailability()).isFalse();
                });

        Mockito.verify(mockService).updateAvailability(position, newAvailability);
    }

    @Test
    void testUpdateAvailabilityNotFound() {
        this.webTestClient.patch()
                .uri(LocationResource.LOCATIONS + LocationResource.AVAILABILITY, "ZZ")
                .bodyValue(Map.of("availability", true))
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    void testDeleteByPosition() {
        LocationService mockService = Mockito.mock(LocationService.class);

        WebTestClient localClient = WebTestClient.bindToController(
                new LocationResource(mockService)
        ).build();

        String position = "B1";

        Mockito.doNothing().when(mockService).deleteByPosition(position);

        localClient.delete()
                .uri(LocationResource.LOCATIONS + LocationResource.POSITION, position)
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(mockService).deleteByPosition(position);
    }

}