package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGamePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class VideoGameServiceIT {

    @Autowired
    private VideoGameService videoGameService;

    @Autowired
    private VideoGamePersistence videoGamePersistence;

    @Test
    void testCreate(){
        VideoGame videoGame = new VideoGame("Halo Infinity",2,true, LocalDate.of(2012,9,16));
        VideoGame serviceVideoGame = this.videoGameService.create(videoGame);
        assertEquals("Halo Infinity",serviceVideoGame.getVideoGameAlias());
        assertEquals(2, serviceVideoGame.getNumberOfPlayer());
        assertEquals(LocalDate.of(2012,9,16), serviceVideoGame.getReleaseDate());
        assertTrue(serviceVideoGame.getCrossPlatform());
    }

    @Test
    void testCreateConflict(){
        VideoGame videoGame = new VideoGame("Halo",2,false, LocalDate.of(2012,9,16));
        assertThrowsExactly(ConflictException.class, () -> this.videoGameService.create(videoGame));
    }
}
