package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGamePersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoGameService {

    private final VideoGamePersistance videoGamePersistance;

    @Autowired
    public VideoGameService(VideoGamePersistance videoGamePersistance) {
        this.videoGamePersistance = videoGamePersistance;
    }
}
