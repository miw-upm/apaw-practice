package es.upm.miw.apaw.functionaltests.metro;
import es.upm.miw.apaw.adapters.resources.metro.TrainStationResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.metro.TrainStationResource.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TrainStationResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testReadCapacityByName() {
        webTestClient.get()
                .uri(TRAIN_STATIONS+NAME+CAPACITY, "Central Station")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(res -> assertEquals("500", res));
    }

    @Test
    void testFindPositionsByUserMobile() {
        BDDMockito.given(this.userRestClient.readByMobile("602123456"))
                .willReturn(UserDto.builder()
                        .id(UUID.fromString("44444444-4444-4444-4444-444444444444"))
                        .mobile("602123456")
                        .build());

        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(TrainStationResource.TRAIN_STATIONS)
                        .queryParam("mobile", "602123456")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> assertThat(body).contains("10"));
    }
}