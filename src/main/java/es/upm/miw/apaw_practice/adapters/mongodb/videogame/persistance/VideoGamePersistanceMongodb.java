package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGamePersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("videoGamePersistance")
public class VideoGamePersistanceMongodb implements VideoGamePersistance {

    private final VideoGameRepository videoGameRepository;

    @Autowired
    public VideoGamePersistanceMongodb(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }
}
