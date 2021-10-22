package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlatformPersistance;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameCompanyService {

    private final VideoGameCompanyPersistance videoGameCompanyPersistance;
    private final PlatformPersistance platformPersistance;

    @Autowired
    public VideoGameCompanyService(VideoGameCompanyPersistance videoGameCompanyPersistance, PlatformPersistance platformPersistance) {
        this.videoGameCompanyPersistance = videoGameCompanyPersistance;
        this.platformPersistance = platformPersistance;
    }

    public VideoGameCompany updatePlatform(String name, List<Platform> platforms) {
        VideoGameCompany videoGameCompany = this.videoGameCompanyPersistance.readByName(name);
        videoGameCompany.setPlatforms(platforms);
        return this.videoGameCompanyPersistance.update(videoGameCompany);
    }
}
