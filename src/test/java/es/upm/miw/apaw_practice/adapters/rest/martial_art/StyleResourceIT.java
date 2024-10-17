package es.upm.miw.apaw_practice.adapters.rest.martial_art;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.StyleResource.STYLE;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.StyleResource.NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class StyleResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Style  style = new Style(
                "Estilo ITF",
                "Estilo que actual no es un deporte olimpico",
                100,
                "Norte america"
        );
        this.webTestClient
                .post()
                .uri(STYLE)
                .body(BodyInserters.fromValue(style))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Style.class)
                .value(Assertions::assertNotNull);
    }
    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(STYLE + NAME, "Estilo de combate")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Style.class)
                .value(Assertions::assertNotNull)
                .value(style -> {
                    assertEquals("Estilo de combate", style.getName());
                    assertEquals("america", style.getOriginCountry());
                    assertEquals("Tecnica pensada para el deporte de contacto", style.getDescription());
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(STYLE + NAME, "Estilo loco")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        Style  style = new Style(
                "Estilo MMA",
                "Estilo  para UFC",
                100,
                "Norte america"
        );
        this.webTestClient
                .post()
                .uri(STYLE)
                .body(BodyInserters.fromValue(style))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Style.class)
                .value(Assertions::assertNotNull);

        this.webTestClient
                .delete()
                .uri(STYLE + NAME, "Estilo MMA")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdate() {
        Style  style = new Style(
                "MMA",
                "Estilo  para UFC",
                100,
                "Norte america"
        );
        this.webTestClient
                .post()
                .uri(STYLE)
                .body(BodyInserters.fromValue(style))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Style.class)
                .value(Assertions::assertNotNull);

        style.setName("TKD");

        this.webTestClient
                .put()
                .uri(STYLE + NAME, "MMA")
                .body(BodyInserters.fromValue(style))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Style.class)
                .value(updatedStyle -> {
                    assertEquals("TKD", updatedStyle.getName());
                });
    }
}
