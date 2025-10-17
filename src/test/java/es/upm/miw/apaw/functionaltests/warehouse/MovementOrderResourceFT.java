package es.upm.miw.apaw.functionaltests.warehouse;

import es.upm.miw.apaw.adapters.resources.warehouse.MovementOrderResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MovementOrderResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreateMovementOrder() {
        UUID mockUserId = UUID.randomUUID();
        BDDMockito.given(this.userRestClient.readById(mockUserId))
                .willReturn(UserDto.builder()
                        .id(mockUserId)
                        .firstName("mockUser")
                        .mobile("123456789")
                        .build());

        MovementOrder movementOrder = MovementOrder.builder()
                .typeOrder("OUTBOUND")
                .partnerName("Client B")
                .partnerAddress("Avenida Siempre Viva 742")
                .completedOrder(false)
                .user(UserDto.builder().id(mockUserId).build())
                .build();

        this.webTestClient.post()
                .uri(MovementOrderResource.MOVEMENT_ORDERS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(movementOrder)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MovementOrder.class)
                .value(response -> {
                    assertThat(response.getId()).isNotNull();
                    assertThat(response.getUser().getId()).isEqualTo(mockUserId);
                });
    }

    @Test
    void testFindPositionsByUserMobile() {
        BDDMockito.given(this.userRestClient.readByMobile("6600006600"))
                .willReturn(UserDto.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .mobile("6600006600")
                        .build());

        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(MovementOrderResource.MOVEMENT_ORDERS)
                        .queryParam("mobile", "6600006600")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> assertThat(body).contains("A1"));
    }

}
