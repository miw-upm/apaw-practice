package es.upm.miw.apaw_practice.adapters.rest.martial_art;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.hotel_retired.HotelResource;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.martial_art.TechniqueResource.TECHNIQUE;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.TechniqueResource.PHONE_NO_REPEAT;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.TechniqueResource.SEARCH;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.TechniqueResource.TOTAL_SUM_TECHNIQUE_NO_REPEAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class TechniqueResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Style style = new Style(
                "Estilo de combate",
                "Tecnica pensada para el deporte de contacto",
                4,
                "america"
        );

        Instructor instructor = new Instructor(
                "Z1554444Z",
                "Robert yellow",
                9789499,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2));
        Technique technique = new Technique(
                "Ap chagui",
                3,
                false,
                1.0,
                style,
                List.of(instructor)
        );
        this.webTestClient
                .post()
                .uri(TECHNIQUE)
                .body(BodyInserters.fromValue(technique))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Technique.class)
                .value(Assertions::assertNotNull);
    }
    @Test
    void testFindNonDuplicatedInstructorPhonesByPopularity() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(TECHNIQUE + SEARCH + PHONE_NO_REPEAT)
                                .queryParam("q", "popularity: 2")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .value(phones -> {
                    assertEquals(3, phones.size());
                    Assertions.assertTrue(phones.contains(978957449));
                });
    }
    @Test
    void testFindTotalDurationNoRepeatByDescription() {
        Integer expectedTotalDuration = 5;
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(TECHNIQUE + SEARCH + TOTAL_SUM_TECHNIQUE_NO_REPEAT)
                                .queryParam("q", "description: Tecnica pensada para el deporte de contacto")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(response -> {
                    assertEquals(expectedTotalDuration, response.getResponseBody());
                });
    }
}
