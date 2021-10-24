package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance.VideoGameCompanyPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestConfig
public class VideoGameCompanyPersistenceMongodbIT {

    @Autowired
    public VideoGameCompanyPersistenceMongodb videoGameCompanyPersistenceMongodb;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testReadAll() {
        Optional<VideoGameCompany> videoGameCompany = this.videoGameCompanyPersistenceMongodb.readAll()
                .filter(company -> "nintendo".equals(company.getName()))
                .findFirst();
        assertTrue(videoGameCompany.isPresent());
        assertNotNull(videoGameCompany.get().getStockMarket());
        assertNotNull(videoGameCompany.get().getPlatforms());
    }

    @Test
    void readByName() {
        Optional<VideoGameCompany> videoGameCompany = Optional.of(this.videoGameCompanyPersistenceMongodb.readByName("nintendo"));
        assertNotNull(videoGameCompany.get().getName());
        assertNotNull(videoGameCompany.get().getPlatforms());
    }

    @Test
    void testUpdate() {
        Optional<VideoGameCompany> videoGameCompany = this.videoGameCompanyPersistenceMongodb.readAll()
                .filter(company -> "nintendo".equals(company.getName()))
                .findFirst();
        assertTrue(videoGameCompany.isPresent());
        List<Platform> platformList = videoGameCompany.get().getPlatforms();
        platformList.add(new Platform("oled", "switch", "256kb"));
        this.videoGameCompanyPersistenceMongodb.update(videoGameCompany.get());

        Optional<VideoGameCompany> newVideoGameCompany = this.videoGameCompanyPersistenceMongodb.readAll()
                .filter(company -> "nintendo".equals(company.getName()))
                .findFirst();
            assertTrue(newVideoGameCompany.isPresent());
        videoGameSeederService.deleteAll();
        videoGameSeederService.seedDatabase();
    }

}
