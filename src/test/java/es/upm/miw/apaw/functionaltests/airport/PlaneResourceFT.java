package es.upm.miw.apaw.functionaltests.airport;

import es.upm.miw.apaw.adapters.resources.airport.PlaneResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.models.airport.PlaneSeatCountUpdating;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PlaneResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreate() {
        Plane plane = Plane.builder()
                .registrationNumber("TestFT0")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.now().minusMonths(3))
                .manufacturer("Airbus")
                .build();

        webTestClient.post()
                .uri(PlaneResource.PLANES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(plane)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Plane.class)
                .value(created -> assertThat(created).isNotNull());
    }

    @Test
    void testCreateReferenceNumberConflict() {
        Plane plane = Plane.builder()
                .registrationNumber("EC-MAD")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.now().minusMonths(3))
                .manufacturer("Airbus")
                .build();

        webTestClient.post()
                .uri(PlaneResource.PLANES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(plane)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateSeatCounts() {
        Plane plane = Plane.builder()
                .registrationNumber("TestFT1")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.now().minusMonths(3))
                .manufacturer("Airbus")
                .build();

        webTestClient.post()
                .uri(PlaneResource.PLANES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(plane)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Plane.class)
                .value(created -> assertThat(created).isNotNull());

        List<PlaneSeatCountUpdating> updates = List.of(
                new PlaneSeatCountUpdating("TestFT1", 190)
        );

        webTestClient.patch()
                .uri(PlaneResource.PLANES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateSeatCountsNotFound() {
        List<PlaneSeatCountUpdating> updates = List.of(
                new PlaneSeatCountUpdating("TestFT2", 190)
        );

        webTestClient.patch()
                .uri(PlaneResource.PLANES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetRegitrationNumberByPilotMobile() {
        UserDto userDto = UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .firstName("user0")
                .mobile("666000660").build();

        BDDMockito.given(this.userRestClient.readByMobile("666000660"))
                .willReturn(userDto);

        webTestClient.get()
                .uri(PlaneResource.PLANES + PlaneResource.PILOT + "/666000660")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(registrationNumbers -> {
                            assertThat(registrationNumbers)
                                    .hasSize(3);
                        }
                );

    }
}
