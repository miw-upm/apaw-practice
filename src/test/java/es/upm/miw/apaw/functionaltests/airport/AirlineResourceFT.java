package es.upm.miw.apaw.functionaltests.airport;

import es.upm.miw.apaw.adapters.resources.airport.AirlineResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class AirlineResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        webTestClient.delete().uri(AirlineResource.AIRLINES + "/UPM Dellines Economy")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testReadByPlaneModel() {
        webTestClient.get().uri(AirlineResource.AIRLINES + "/B787-9 Dreamliner")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(names -> {
                            assertThat(names)
                                    .hasSize(2);
                        }
                );
    }
}
