package es.upm.miw.apaw.functionaltests.videogame;


import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameSeeder;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class LikeListResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Autowired
    private VideogameSeeder videogameSeeder;
    @BeforeEach
    void setUp() {
        videogameSeeder.deleteAll();
        videogameSeeder.seedDatabase();
    }

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
    @Test
    void testObtainMobilesBySector() {
        String sector = "sector1";

        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation -> {
                    UUID userId = invocation.getArgument(0);
                    if (userId.equals(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))) {
                        return UserDto.builder()
                                .id(userId)
                                .mobile("222222222")
                                .firstName("UserA")
                                .build();
                    } else if (userId.equals(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"))) {
                        return UserDto.builder()
                                .id(userId)
                                .mobile("333333333")
                                .firstName("UserB")
                                .build();
                    } else {
                        return UserDto.builder()
                                .id(userId)
                                .mobile("999999999")
                                .firstName("Unknown")
                                .build();
                    }
                });

        webTestClient.get()
                .uri(LIKE_LISTS + SECTOR + SECTOR_MOBILE, sector)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0]").isEqualTo("222222222");

    }


}


