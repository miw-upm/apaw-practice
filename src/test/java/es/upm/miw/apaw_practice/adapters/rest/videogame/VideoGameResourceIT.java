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
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.videogame.VideoGameResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class VideoGameResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        VideoGame videoGame =
                new VideoGame("Metroid",1,false, LocalDate.of(2015,2,5));
        this.webTestClient.post()
                .uri(VIDEOGAMES)
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
                .uri(VIDEOGAMES)
                .body(BodyInserters.fromValue(videoGame))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate(){
        VideoGame videoGame = new VideoGame("PUBG",1,false, LocalDate.of(2016,3,30));
        this.webTestClient.put()
                .uri(VIDEOGAMES + VIDEOGAMES, "law")
                .body(BodyInserters.fromValue(videoGame))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindPlayerNamesByVideoGameAlias(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(VIDEOGAMES + SEARCH + PLAYERNAMES_BY_VIDEOGAMEALIAS)
                                .queryParam("l","videoGameAlias:Halo")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(name -> assertEquals(List.of("Luis","Melba"),name));
    }

    @Test
    void testSumNumberOfPlayerByPlayerNameAndWebsite(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(VIDEOGAMES + SEARCH + NUMBEROFPLAYERS_BY_PLAYERNAME_AND_WEBSITE)
                                .queryParam("l","playerName: Nelson;website: www.nanoby.com")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .value(name -> assertEquals(360,name));
    }
}