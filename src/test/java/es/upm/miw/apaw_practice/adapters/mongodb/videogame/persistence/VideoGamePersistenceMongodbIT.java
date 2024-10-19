package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class VideoGamePersistenceMongodbIT {

    @Autowired
    private VideoGamePersistenceMongodb videoGamePersistence;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.videoGamePersistence.read("SAO"));
    }

    @Test
    void testVideoGameAliasNotExits(){
        assertFalse(this.videoGamePersistence.existVideoGameAlias("SAO"));
    }

    @Test
    void testVideoGameAliasExists(){
        assertTrue(this.videoGamePersistence.existVideoGameAlias("Halo"));
    }

    @Test
    void testCreateAndRead(){
        VideoGame videoGame =
                new VideoGame("Dante",1,true, LocalDate.of(2020,5,16));
        this.videoGamePersistence.create(videoGame);
        VideoGame videoGameBD = this.videoGamePersistence.read("Dante");
        assertEquals(1, videoGameBD.getNumberOfPlayer());
        assertEquals(LocalDate.of(2020,5,16), videoGameBD.getReleaseDate());
        assertTrue(videoGameBD.getCrossPlatform());
    }

    @Test
    void testUpdate(){
        Optional<VideoGame> videoGameSelected = this.videoGamePersistence.readAll()
                .filter(videoGames -> "Dante Inferno".equals(videoGames.getVideoGameAlias()))
                .findFirst();
        assertTrue(videoGameSelected.isPresent());

        videoGameSelected.get().setNumberOfPlayer(2);
        videoGameSelected.get().setReleaseDate(LocalDate.of(2020,5,16));
        videoGameSelected.get().setCrossPlatform(false);

        this.videoGamePersistence.update("Dante", videoGameSelected.get());
        Optional<VideoGame> newVideoGame = this.videoGamePersistence.readAll()
                .filter(videoGame -> "Dante".equals(videoGame.getVideoGameAlias()))
                .findFirst();
        assertTrue(newVideoGame.isPresent());

        assertEquals(1, newVideoGame.get().getNumberOfPlayer());
        assertEquals(LocalDate.of(2020,5,16), newVideoGame.get().getReleaseDate());
        assertTrue(newVideoGame.get().getCrossPlatform());
        videoGameSeederService.deleteAll();
        videoGameSeederService.seedDatabase();
    }

    @Test
    void findPlayerNameByVideoGameAlias(){
        Stream<String> videoGameAlias = videoGamePersistence.findPlayerNameByVideoGameAlias("Halo");
        assertEquals(List.of("Luis","Melba"), videoGameAlias.toList());
    }
}
