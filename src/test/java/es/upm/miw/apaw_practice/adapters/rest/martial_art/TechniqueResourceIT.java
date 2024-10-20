package es.upm.miw.apaw_practice.adapters.rest.martial_art;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.List;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.TechniqueResource.TECHNIQUE;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.TechniqueResource.POPULARITY;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.TechniqueResource.NAME;
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
        int popularity = 2;  // Puedes ajustar este valor según el caso de prueba que quieras verificar

        this.webTestClient
                .get()
                .uri(TECHNIQUE + POPULARITY, popularity)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .value(phones -> {
                    assertEquals(3, phones.size());  // Ajusta el tamaño según lo esperado
                    Assertions.assertTrue(phones.contains(978957449));  // Verifica que el número esperado esté presente
                });
    }
}
