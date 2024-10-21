package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.course.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RestTestConfig
class VideoResourceIT {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testUpdateVideo() {
        String videoName = "Introducción a Python";

        Video updatedVideo = new Video();
        updatedVideo.setName(videoName);
        updatedVideo.setDuration(LocalTime.of(0, 20)); // Cambiar la duración
        updatedVideo.setCreationDate(LocalDateTime.now()); // Fecha de lanzamiento

        this.webTestClient
                .put()
                .uri(VideoResource.VIDEOS + "/{name}", videoName)
                .body(BodyInserters.fromValue(updatedVideo))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Video.class)
                .value(video -> {
                    assertThat(video.getName()).isEqualTo(updatedVideo.getName());
                    assertThat(video.getDuration()).isEqualTo(updatedVideo.getDuration());
                });
    }

    @Test
    void testUpdateCourse() {
        String videoName = "POO en Python";
        String newCourseTitle = "Curso de POO en Python";

        this.webTestClient
                .patch()
                .uri(VideoResource.VIDEOS + "/{name}", videoName)
                .body(BodyInserters.fromValue(newCourseTitle))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Video.class);
    }
}
