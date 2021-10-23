package es.upm.miw.apaw_practice.adapters.mongodb.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlatformRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameCompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.CriticEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlatformEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameCompanyEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameEntity;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Service
public class VideoGameSeederService {

    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private VideoGameCompanyRepository videoGameCompanyRepository;
    @Autowired
    private VideoGameRepository videoGameRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- VideoGame Initial Load -----------");

        PlatformEntity[] platforms = {
            new PlatformEntity(new Platform("switch", "oled", "64gb")),
            new PlatformEntity(new Platform("wiiu", "deluxe", "8gb")),
            new PlatformEntity(new Platform("xbox", "series x", "1tb")),
            new PlatformEntity(new Platform("xbox", "one s", "1tb")),
            new PlatformEntity(new Platform("playstation", "ps5", "825gb")),
            new PlatformEntity(new Platform("playstation", "ps4", "1tb")),
        };
        this.platformRepository.saveAll(Arrays.asList(platforms));

        VideoGameCompanyEntity[] companies = {
            new VideoGameCompanyEntity("nintendo", LocalDate.of(1889, 9, 23), true, List.of(platforms[0], platforms[1])),
            new VideoGameCompanyEntity("microsoft", LocalDate.of(1975, 4, 4), true, List.of(platforms[2], platforms[3])),
            new VideoGameCompanyEntity("sony", LocalDate.of(1946, 5, 7), true, List.of(platforms[4], platforms[5]))
        };
        this.videoGameCompanyRepository.saveAll(Arrays.asList(companies));

        VideoGameEntity[] videogames = {
            new VideoGameEntity("nba 2k21", LocalDate.of(2020, 9, 3), "e", new CriticEntity(false, 79, 2.5), List.of(platforms[0], platforms[3], platforms[5])),
            new VideoGameEntity("ratchet & clank: rift apart", LocalDate.of(2021, 6, 11), "e10+", new CriticEntity(false, 88, 8.7), List.of(platforms[4])),
            new VideoGameEntity("forza horizon 4", LocalDate.of(2020, 9, 3), "e", new CriticEntity(true, 92, 8.2), List.of(platforms[2], platforms[3])),
            new VideoGameEntity("bayonetta 2", LocalDate.of(2014, 9, 20), "e", new CriticEntity(true, 92, 8.5), List.of(platforms[0], platforms[1])),
            new VideoGameEntity("xenoblade chronicles x", LocalDate.of(2015, 4, 29), "e", new CriticEntity(false, 84, 9.1), List.of(platforms[1]))
        };
        this.videoGameRepository.saveAll(Arrays.asList(videogames));
    }

    public void deleteAll() {
        this.videoGameCompanyRepository.deleteAll();
        this.videoGameRepository.deleteAll();
        this.platformRepository.deleteAll();
    }

}
