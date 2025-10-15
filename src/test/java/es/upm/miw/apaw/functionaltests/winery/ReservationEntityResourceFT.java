package es.upm.miw.apaw.functionaltests.winery;

import es.upm.miw.apaw.adapters.resources.winery.ReservationResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.winery.Reservation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.models.winery.Wine;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class ReservationEntityResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreate() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(invocation.getArgument(0)).mobile("123456789").firstName("mock").build());

        Reservation reservation = Reservation.builder()
                .user(UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")).build())
                .tastingSession(TastingSession.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"))
                        .build())
                .build();

        webTestClient.post()
                .uri(ReservationResource.RESERVATION)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(reservation)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindReservationIdsByWineName() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> UserDto.builder()
                        .id(invocation.getArgument(0))
                        .mobile("123456789")
                        .firstName("mock")
                        .build());

        UUID tastingSessionId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");

        Reservation reservation = Reservation.builder()
                .user(UserDto.builder().id(UUID.randomUUID()).build())
                .tastingSession(TastingSession.builder()
                        .id(tastingSessionId)
                        .wines(List.of(Wine.builder().name("Merlot").build()))
                        .build())
                .build();

        Reservation created = webTestClient.post()
                .uri(ReservationResource.RESERVATION)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(reservation)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reservation.class)
                .returnResult()
                .getResponseBody();

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ReservationResource.RESERVATION + ReservationResource.SEARCH_BY_WINE_NAME)
                        .queryParam("name", "Merlot")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UUID.class)
                .value(list -> assertThat(list).contains(created.getId()));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ReservationResource.RESERVATION + ReservationResource.SEARCH_BY_WINE_NAME)
                        .queryParam("name", "Cabernet")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UUID.class)
                .value(list -> assertThat(list).isEmpty());
    }
}
