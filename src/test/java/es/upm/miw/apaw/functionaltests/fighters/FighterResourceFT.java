package es.upm.miw.apaw.functionaltests.fighters;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.fighters.Coach;
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
        body.setScore(7);
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

    @Test
    void testDeleteRating_whenExists_returns204_andRemoves() {
        String nickname = "Spider";
        UUID ratingId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");

        this.webTestClient.delete()
                .uri(FIGHTERS + NICK_ID + RATINGS + RATING_ID, nickname, ratingId)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteRating_whenNotExistsInFighter_returns404() {
        String nickname = "The Dragon";
        UUID notExisting = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0999");

        this.webTestClient.delete()
                .uri(FIGHTERS + NICK_ID + RATINGS + RATING_ID, nickname, notExisting)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteRating_whenFighterHasNoRatings_returns404() {
        String nickname = "Iron";
        UUID anyId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0999");

        this.webTestClient.delete()
                .uri(FIGHTERS + NICK_ID + RATINGS + RATING_ID, nickname, anyId)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchWinsOk() {
        Coach coach = Coach.builder()
                .fullName("Carlos Mendes")
                .academy("Gracie Team")
                .experienceYears(20)
                .build();
        Fighter fighter = Fighter.builder()
                .nickname("Spider")
                .name("Anderson")
                .lastName("Silva")
                .country("Brazil")
                .weight(84.0)
                .height(1.88)
                .wins(99)
                .losses(11)
                .coach(coach)
                .build();

        webTestClient.patch()
                .uri(FIGHTERS + NICK_ID, "Spider")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(fighter)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Fighter.class)
                .value(f -> {
                    assertThat(f).isNotNull();
                    assertThat(f.getNickname()).isEqualTo("Spider");
                    assertThat(f.getWins()).isEqualTo(fighter.getWins());
                });
    }

    @Test
    void testPatchWinsNotFound() {
        Coach coach = Coach.builder()
                .fullName("Carlos Mendes")
                .academy("Gracie Team")
                .experienceYears(20)
                .build();
        Fighter fighter = Fighter.builder()
                .nickname("Spider")
                .name("Anderson")
                .lastName("Silva")
                .country("Brazil")
                .weight(84.0)
                .height(1.88)
                .wins(34)
                .losses(11)
                .coach(coach)
                .build();
        webTestClient.patch()
                .uri(FIGHTERS + NICK_ID, "No Existe")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(fighter)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchWinsBadRequest() {
        Fighter fighter = new Fighter();
        fighter.setWins(-1);
        webTestClient.patch()
                .uri(FIGHTERS + NICK_ID, "Spider")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(fighter)
                .exchange()
                .expectStatus().isBadRequest();
    }
    @Test
    void testGetRatingsDistinctByAcademy_ok_tokyoDojo() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FIGHTERS + "/ratings-distinct-by-academy")
                        .queryParam("academy", "Tokyo Dojo")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.comments[0]").isEqualTo("Incredible striking!")
                .jsonPath("$.comments[1]").isEqualTo("Needs better cardio")
                .jsonPath("$.comments.length()").isEqualTo(2);
    }

    @Test
    void testGetRatingsDistinctByAcademy_ok_moscowClub() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FIGHTERS + "/ratings-distinct-by-academy")
                        .queryParam("academy", "Moscow Combat Club")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.comments").isArray()
                .jsonPath("$.comments.length()").isEqualTo(3)
                .jsonPath("$.comments").value(list -> {
                    var s = list.toString();
                    assertThat(s).contains("Excellent fighter!");
                    assertThat(s).contains("Incredible striking!");
                    assertThat(s).contains("Poor ground defense");
                });
    }

    @Test
    void testGetRatingsDistinctByAcademy_noResults_empty() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(FIGHTERS + "/ratings-distinct-by-academy")
                        .queryParam("academy", "No Academy")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.comments.length()").isEqualTo(0);
    }
}