package es.upm.miw.apaw.functionaltests.studentcouncil;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class RepresentativeResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testGetAllRepresentatives() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder()
                                .id(invocation.getArgument(0))
                                .mobile("123456789")
                                .firstName("mockUser")
                                .build());

        webTestClient.get()
                .uri("/representatives")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].representative.id").isNotEmpty()
                .jsonPath("$[0].representative.mobile").isEqualTo("123456789")
                .jsonPath("$[0].representative.firstName").isEqualTo("mockUser");
    }

    @Test
    void testFindUserMobilesByReplyReason() throws Exception {
        BDDMockito.given(userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000")))
                .willReturn(new UserDto(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"),
                        "666000660", "user0"));

        BDDMockito.given(userRestClient.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001")))
                .willReturn(new UserDto(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"),
                        "666000661", "user1"));

        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/representatives/mobiles")
                        .queryParam("reason", "Reply1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> {
                    ObjectMapper mapper = new ObjectMapper();
                    List<String> mobiles = null;
                    try {
                        mobiles = mapper.readValue(body, new TypeReference<List<String>>() {});
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    assertThat(mobiles).containsExactlyInAnyOrder("666000660", "666000661");
                });
    }
}