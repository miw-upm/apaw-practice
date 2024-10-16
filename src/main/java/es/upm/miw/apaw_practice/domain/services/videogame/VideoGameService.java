package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoGameService {
    private final VideoGamePersistence videoGamePersistence;

    @Autowired
    public VideoGameService(VideoGamePersistence videoGamePersistence) {
        this.videoGamePersistence = videoGamePersistence;
    }
    public VideoGame create(VideoGame videoGame) {
        this.assertVideoGameAliasNotExist(videoGame.getVideoGameAlias());
        return this.videoGamePersistence.create(videoGame);
    }
    public void assertVideoGameAliasNotExist(String videoGameAlias) {
        if(this.videoGamePersistence.existVideoGameAlias(videoGameAlias)) {
            throw new ConflictException("VideoGameAlias " + videoGameAlias + " already exists");
        }
    }

    public VideoGame update(String videoGameAlias, VideoGame videoGame) {
        return this.videoGamePersistence.update(videoGameAlias, videoGame);
    }
}
