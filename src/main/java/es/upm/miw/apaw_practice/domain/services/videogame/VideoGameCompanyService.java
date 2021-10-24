package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlatformPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameCompanyService {

    private final VideoGameCompanyPersistence videoGameCompanyPersistence;
    private final PlatformPersistence platformPersistence;

    @Autowired
    public VideoGameCompanyService(VideoGameCompanyPersistence videoGameCompanyPersistence, PlatformPersistence platformPersistence) {
        this.videoGameCompanyPersistence = videoGameCompanyPersistence;
        this.platformPersistence = platformPersistence;
    }

    public VideoGameCompany updatePlatform(String name, List<Platform> platforms) {
        VideoGameCompany videoGameCompany = this.videoGameCompanyPersistence.readByName(name);
        videoGameCompany.setPlatforms(platforms);
        return this.videoGameCompanyPersistence.update(videoGameCompany);
    }
}
