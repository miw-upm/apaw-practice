package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGamerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class VideoGameRepositoryIT {

    @Autowired
    private VideoGameRepository videoGameRepository;

    @Test
    void testFindByVideoGameAlias(){
        assertTrue(this.videoGameRepository.findByVideoGameAlias("Halo").isPresent());
        VideoGamerEntity videoGame = this.videoGameRepository.findByVideoGameAlias("Halo").get();
        assertEquals(2, videoGame.getNumberOfPlayer());
        assertEquals(LocalDate.of(2012,9,16), videoGame.getReleaseDate());
        assertTrue(videoGame.getCrossPlatform());
    }
}