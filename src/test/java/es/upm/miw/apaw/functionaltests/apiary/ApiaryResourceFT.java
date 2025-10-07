package es.upm.miw.apaw.functionaltests.apiary;

import es.upm.miw.apaw.adapters.resources.apiary.ApiaryResource;
import es.upm.miw.apaw.domain.models.apiary.Apiary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ApiaryResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByLocation() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiaryResource.APIARIES)
                        .queryParam("location", "Burgos")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Apiary.class)
                .value(apiaries -> assertThat(apiaries)
                        .extracting(Apiary::getLocation)
                        .allMatch(location -> location.equals("Burgos")));
    }
}
