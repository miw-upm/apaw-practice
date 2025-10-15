package es.upm.miw.apaw.functionaltests.videogame;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static es.upm.miw.apaw.adapters.resources.shop.TagResource.NAME_ID;
import static es.upm.miw.apaw.adapters.resources.videogame.VideogameResource.VIDEOGAMES;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class VideogameResourceFT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private VideogameRepository videogameRepository;


    @Test
    void testDelete(){

        webTestClient.delete()
                .uri(VIDEOGAMES + NAME_ID,"game0")
                .exchange()
                .expectStatus().isOk();

    }
    @Test
    void testUpdateOnlineByGenre() {
        webTestClient.patch()
                .uri(VIDEOGAMES+"/genre/action/online?online=false")
                .exchange()
                .expectStatus().isOk();


        }

}
