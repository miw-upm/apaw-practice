package es.upm.miw.apaw.functionaltests.football;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.football.FootballPlayer;
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

import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.football.FootballPlayerResource.PLAYERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class FootballPlayerResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    private static UserDto mockUser() {
        return UserDto.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .mobile("600123456")
                .firstName("Carlos")
                .build();
    }

    @Test
    void testReadByNickname_ok() {
        this.webTestClient.get()
                .uri(PLAYERS + "/{nickname}", "Rafa")
                .exchange()
                .expectStatus().isOk()
                .expectBody(FootballPlayer.class)
                .value(player -> {
                    assertThat(player).isNotNull();
                    assertThat(player.getNickname()).isEqualTo("Rafa");
                    assertThat(player.getGoalsScored()).isGreaterThanOrEqualTo(0);
                });
    }

    @Test
    void testReadByNickname_notFound() {
        this.webTestClient.get()
                .uri(PLAYERS + "/{nickname}", "noExiste")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetMobilesByNickname_ok() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willReturn(mockUser());

        this.webTestClient.get()
                .uri(PLAYERS + "/{nickname}/mobiles", "Rafa")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.mobiles").isArray()
                .jsonPath("$.mobiles[0]").isEqualTo("600123456");
    }

    @Test
    void testGetMobilesByNickname_notFound() {
        this.webTestClient.get()
                .uri(PLAYERS + "/{nickname}/mobiles", "noExiste")
                .exchange()
                .expectStatus().isNotFound();
    }
}