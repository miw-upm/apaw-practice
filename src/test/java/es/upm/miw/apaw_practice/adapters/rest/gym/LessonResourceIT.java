package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gym.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class LessonResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByTest() {
        this.webTestClient

                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(LessonResource.Lessons + LessonResource.Search)
                                .queryParam("title", "RPM")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Lesson.class)
                .value(lessons -> assertEquals("lesMils", lessons.get(0).getDescription()))
                .value(lessons -> assertEquals(LocalDateTime.of(2020, 12, 2, 1, 45), lessons.get(0).getTime()))
                .value(lessons -> assertEquals("lesMils", lessons.get(0).getDescription()))
                .value(lessons -> assertEquals(true, lessons.get(0).getFinished()));
    }
}
