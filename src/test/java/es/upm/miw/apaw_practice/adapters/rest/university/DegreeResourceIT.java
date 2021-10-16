package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.university.DegreeResource.DEGREES;
import static es.upm.miw.apaw_practice.adapters.rest.university.DegreeResource.TITLE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class DegreeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadTitles() {
        this.webTestClient
                .get()
                .uri(DEGREES + TITLE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .consumeWith(entityList -> {
                    assertNotNull(entityList.getResponseBody());
                    List<String> titles = entityList.getResponseBody();
                    assertTrue(titles.containsAll(Arrays.asList(
                            "Máster en Ingeniería Web",
                            "Ingeniería del Software",
                            "Ingeniería de Computadores"
                    )));
                });
    }
}
