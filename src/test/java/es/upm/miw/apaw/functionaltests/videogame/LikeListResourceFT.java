package es.upm.miw.apaw.functionaltests.videogame;


import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.videogame.LikeListResource.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class LikeListResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testReadSharedById() {
        webTestClient.get()
                .uri(LIKE_LISTS + ID_ID + SHARED, UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0020"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .value(Assertions::assertTrue);
    }

    @Test
    void testObtainSectorsByMobile() {
        String mobile = "123123123";

        BDDMockito.given(this.userRestClient.readByMobile(any(String.class)))
                .willAnswer(invocation ->
                        UserDto.builder()
                                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003")) // usuario del seeder
                                .mobile(invocation.getArgument(0))
                                .firstName("mock")
                                .build()
                );


        webTestClient.get()
                .uri(LIKE_LISTS + MOBILE + GAME_SECTOR, mobile)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0]").isEqualTo("sector0")
                .jsonPath("$[1]").isEqualTo("sector1");

    }

}


