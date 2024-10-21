package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.adapters.mongodb.course.daos.TutoringSessionRepository;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;


import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class TutoringSessionResourceIT {


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TutoringSessionRepository tutoringSessionRepository;

    @Test
    void testDeleteTutoringSessionFound() {
        String title = "Lenguaje C Básico 1"; // Asegúrate de que este título existe en tu base de datos

        // Elimina la sesión de tutoría a través de WebTestClient
        this.webTestClient
                .delete()
                .uri(TutoringSessionResource.TUTORINGSESSIONS + TutoringSessionResource.TITTLE, title)
                .exchange()
                .expectStatus().isOk();

        // Verifica que la sesión se haya eliminado
        assertFalse(tutoringSessionRepository.existsByTitle(title));
    }

    @Test
    void testDeleteTutoringSessionNotFound() {
        String title = "Non-existent Session";

        // Intenta eliminar una sesión que no existe
        this.webTestClient
                .delete()
                .uri(TutoringSessionResource.TUTORINGSESSIONS + TutoringSessionResource.TITTLE, title)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Not Found Exception (404). Tuttoring Session: " + title);
    }

    @Test
    void testPriceSumOfRoleDuration() {
        String role = "STUDENT"; // Este debe ser un rol válido
        LocalTime duration = LocalTime.of(0, 30); // Un ejemplo de duración

        // Cambia la forma en que se envían los datos para que no se utilice el cuerpo
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(TutoringSessionResource.TUTORINGSESSIONS + TutoringSessionResource.ROLE_PRICE)
                        .queryParam("role", role)
                        .queryParam("duration", duration.toString())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .consumeWith(response -> {
                    BigDecimal price = response.getResponseBody();
                    assertTrue(price.compareTo(BigDecimal.ZERO) > 0);
                });
    }


    @Test
    void testPriceSumOfRoleDurationInvalidRole() {
        String role = "INVALID_ROLE";
        LocalTime duration = LocalTime.of(0, 30);

        // Intenta calcular el precio con un rol no válido
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(TutoringSessionResource.TUTORINGSESSIONS + TutoringSessionResource.ROLE_PRICE)
                        .queryParam("role", role)
                        .queryParam("duration", duration.toString())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .consumeWith(response -> {
                    BigDecimal price = response.getResponseBody();
                    assertFalse(price.compareTo(BigDecimal.ZERO) > 0);
                });
    }
}