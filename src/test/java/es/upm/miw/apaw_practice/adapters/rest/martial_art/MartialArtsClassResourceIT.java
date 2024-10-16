package es.upm.miw.apaw_practice.adapters.rest.martial_art;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;

import es.upm.miw.apaw_practice.domain.models.martial_art.MartialArtsClass;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.MartialArtClassResource.MARTIALARTSCLASS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig

public class MartialArtsClassResourceIT {
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
        MartialArtsClass martialArtsClass = new MartialArtsClass("Muay thai",
                                                                 LocalDate.of(2024, 10, 7),
                                                        "Brutal kick",
                                                                  List.of(technique));
        this.webTestClient
                .post()
                .uri(MARTIALARTSCLASS)
                .body(BodyInserters.fromValue(martialArtsClass))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MartialArtsClass.class)
                .value(Assertions::assertNotNull);
    }
}
