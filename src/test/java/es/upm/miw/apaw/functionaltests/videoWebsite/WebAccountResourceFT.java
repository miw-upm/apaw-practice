package es.upm.miw.apaw.functionaltests.videoWebsite;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
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

import static es.upm.miw.apaw.adapters.resources.videoWebsite.WebAccountResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class WebAccountResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testReadById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010");

        webTestClient.get()
                .uri("/videoWebsite/webAccount/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(WebAccount.class)
                .value(webAccount -> {
                    assertEquals(id, webAccount.getId());
                    assertEquals("Account 1", webAccount.getUserName());
                });
    }

    @Test
    void testObtainTotalViewsByMobile() {
        BDDMockito.given(this.userRestClient.readByMobile(any(String.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                                .mobile(invocation.getArgument(0))
                                .firstName("mock").build());

        webTestClient.get()
                .uri(WEBACCOUNT + MOBILE + TOTAL_VIEWS, "123456789")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .value(result -> assertEquals(80000, result));

    }

}
