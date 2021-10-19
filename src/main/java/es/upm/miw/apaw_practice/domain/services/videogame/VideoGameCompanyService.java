package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGameCompanyPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoGameCompanyService {

    private final VideoGameCompanyPersistance videoGameCompanyPersistance;

    @Autowired
    public VideoGameCompanyService(VideoGameCompanyPersistance videoGameCompanyPersistance) {
        this.videoGameCompanyPersistance = videoGameCompanyPersistance;
    }
}
