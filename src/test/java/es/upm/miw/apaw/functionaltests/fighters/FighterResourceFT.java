package es.upm.miw.apaw.functionaltests.fighters;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.models.fighters.Rating;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.fighters.FighterResource.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class FighterResourceFT {

    @Autowired
    private WebTestClient webTestClient;
    @MockitoBean
    private UserRestClient userRestClient;
    private static UserDto user0FromSeeder() {
        return UserDto.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("666000660")
                .firstName("user0")
                .build();
    }

    @Test
    void testReadByNickname_ok() {
        webTestClient.get()
                .uri(FIGHTERS + NICK_ID, "Spider")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Fighter.class)
                .value(fighter -> {
                    assertThat(fighter).isNotNull();
                    assertThat(fighter.getNickname()).isEqualTo("Spider");
                    assertThat(fighter.getCountry()).isEqualTo("Brazil");
                    assertThat(fighter.getWins()).isEqualTo(34);
                });
    }

    @Test
    void testReadByNickname_notFound() {
        webTestClient.get()
                .uri(FIGHTERS + NICK_ID, "no-existe")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateRating_ok_thenReadFighter_containsUserId() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> UserDto.builder()
                        .id(invocation.getArgument(0))
                        .mobile("123456789")
                        .firstName("mock")
                        .build());

        UserDto bodyUser = user0FromSeeder();

        Rating body = new Rating();
        body.setScore(5);
        body.setComment("Excelente peleador");
        body.setUser(bodyUser);

        webTestClient.post()
                .uri(FIGHTERS + NICK_ID + RATINGS, "Spider")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Rating.class)
                .value(r -> {
                    assertThat(r.getId()).isNotNull();
                    assertThat(r.getScore()).isEqualTo(5);
                    assertThat(r.getComment()).isEqualTo("Excelente peleador");
                    assertThat(r.getUser()).isNotNull();
                    assertThat(r.getUser().getId()).isEqualTo(bodyUser.getId());
                    assertThat(r.getUser().getMobile()).isEqualTo("123456789");
                    assertThat(r.getUser().getFirstName()).isEqualTo("mock");
                });

        webTestClient.get()
                .uri(FIGHTERS + NICK_ID, "Spider")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Fighter.class)
                .value(f -> {
                    assertThat(f).isNotNull();
                    assertThat(f.getNickname()).isEqualTo("Spider");
                    assertThat(f.getRatings()).extracting(Rating::getUser)
                            .extracting(UserDto::getId)
                            .contains(bodyUser.getId());
                    assertThat(f.getRatings().stream().map(Rating::getScore).toList())
                            .contains(5);
                });
    }

    @Test
    void testCreateRating_invalidScore_returnsBadRequest() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> UserDto.builder()
                        .id(invocation.getArgument(0))
                        .mobile("123456789")
                        .firstName("mock")
                        .build());

        UserDto bodyUser = user0FromSeeder();

        Rating body = new Rating();
        body.setScore(7); // > 5
        body.setComment("fuera de rango");
        body.setUser(bodyUser);

        webTestClient.post()
                .uri(FIGHTERS + NICK_ID + RATINGS, "Spider")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateRating_fighterNotFound_returnsNotFound() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> UserDto.builder()
                        .id(invocation.getArgument(0))
                        .mobile("123456789")
                        .firstName("mock")
                        .build());

        UserDto bodyUser = user0FromSeeder();

        Rating body = new Rating();
        body.setScore(3);
        body.setComment("ok");
        body.setUser(bodyUser);

        webTestClient.post()
                .uri(FIGHTERS + NICK_ID + RATINGS, "no-existe")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isNotFound();
    }

}