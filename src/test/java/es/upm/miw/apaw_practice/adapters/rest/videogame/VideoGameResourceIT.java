package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;

@RestTestConfig
public class VideoGameResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private VideoGameResource videoGameResource;

    @Test
    void testCreate(){
        VideoGame videoGame =
                new VideoGame("Metroid",1,false, LocalDate.of(2015,2,5));
        this.webTestClient.post()
                .uri(videoGameResource.VIDEOGAMES)
                .body(BodyInserters.fromValue(videoGame))
                .exchange()
                .expectStatus().isOk()
                .expectBody(VideoGame.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict(){
        VideoGame videoGame = new VideoGame("Zelda",2,true, LocalDate.of(2024,6,15));
        this.webTestClient.post()
                .uri(videoGameResource.VIDEOGAMES)
                .body(BodyInserters.fromValue(videoGame))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate(){
        VideoGame videoGame = new VideoGame("PUBG",1,false, LocalDate.of(2016,3,30));
        this.webTestClient.put()
                .uri(videoGameResource.VIDEOGAMES + videoGameResource.VIDEOGAMES, "law")
                .body(BodyInserters.fromValue(videoGame))
                .exchange()
                .expectStatus().isNotFound();
    }
}
