package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.course.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class UserCourseIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateUser() {
        User newUser = new User("Maria", "maria@gmail.com", User.TypeUser.STUDENT);

        this.webTestClient
                .post()
                .uri(UserCourseResource.USERS)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(newUser))
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(user -> {
                    assertNotNull(user.getEmail());
                    assertEquals("Maria", user.getFirstName());
                    assertEquals("maria@gmail.com", user.getEmail());
                });
    }
    @Test
    void testEmailsOfTitleTutoringSession() {
        String title = "Spring Security"; // Asegúrate de que este título existe en tu base de datos

        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(UserCourseResource.USERS + UserCourseResource.EMAIL_TUTORING)
                        .queryParam("title", title)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(emails -> {
                    // Imprimir los correos electrónicos devueltos para depuración
                    System.out.println("Holi" + emails);

                    assertFalse(emails.isEmpty());
                    assertTrue(emails.get(0).contains("sofia@gmail.com"));
                    assertTrue(emails.get(0).contains("raul@gmail.com"));
                    assertTrue(emails.get(0).contains("daniel@gmail.com"));
                    assertTrue(emails.get(0).contains("lucia@gmail.com"));
                    assertTrue(emails.get(0).contains("andrea@gmail.com"));
                    assertTrue(emails.get(0).contains("pablo@gmail.com"));
                });
    }
    @Test
    void testEmailsOfTitleTutoringSessionNotFound() {
        String title = "Non-existent Title"; // Un título que no debería existir

        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(UserCourseResource.USERS + UserCourseResource.EMAIL_TUTORING)
                        .queryParam("title", title)
                        .build())
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Not Found Exception (404). No Title: " + title);
    }

}
