package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VideoGameService {

    private final VideoGamePersistence videoGamePersistance;

    private final VideoGameCompanyPersistence videoGameCompanyPersistence;

    @Autowired
    public VideoGameService(VideoGamePersistence videoGamePersistance, VideoGameCompanyPersistence videoGameCompanyPersistence) {
        this.videoGamePersistance = videoGamePersistance;
        this.videoGameCompanyPersistence = videoGameCompanyPersistence;
    }

    public VideoGame read(String title) {
        return this.videoGamePersistance.readByTitle(title);
    }

    public void delete(String title) {
        this.videoGamePersistance.delete(title);
    }

    public Stream<VideoGame> findByPlatformsInVideoGameCompany() {
        List<String> consoleNames = this.videoGameCompanyPersistence.readAll()
                .flatMap(videoGameCompany -> videoGameCompany.getPlatforms().stream())
                .map(Platform::getConsoleName)
                .distinct()
                .collect(Collectors.toList());
        return this.videoGamePersistance.readAll()
                .filter(videoGame -> videoGame.getPlatforms().stream()
                        .map(Platform::getConsoleName)
                        .anyMatch(consoleNames::contains));
    }
}
