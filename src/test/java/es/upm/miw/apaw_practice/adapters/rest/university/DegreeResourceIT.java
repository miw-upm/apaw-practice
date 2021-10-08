package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Degree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static es.upm.miw.apaw_practice.adapters.rest.university.DegreeResource.DEGREES;
import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class DegreeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(DEGREES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .consumeWith(entityList -> {
                    assertNotNull(entityList.getResponseBody());
                    List<String> titleList = entityList.getResponseBody();
                    assertFalse(titleList.containsAll(Arrays.asList(
                            "Máster en Ingeniería Web",
                            "Ingeniería del Software",
                            "Ingeniería de Computadores"
                    )));
                });
    }
}
