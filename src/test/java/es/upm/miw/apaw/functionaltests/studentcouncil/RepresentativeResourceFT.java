package es.upm.miw.apaw.functionaltests.studentcouncil;


import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.studentcouncil.Representative;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import es.upm.miw.apaw.domain.services.studentcouncil.RepresentativeService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

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
}