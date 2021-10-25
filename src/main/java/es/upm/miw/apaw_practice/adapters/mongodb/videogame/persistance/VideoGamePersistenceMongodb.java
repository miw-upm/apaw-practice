package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.VideoGameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.VideoGamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("videoGamePersistence")
public class VideoGamePersistenceMongodb implements VideoGamePersistence {

    private final VideoGameRepository videoGameRepository;

    @Autowired
    public VideoGamePersistenceMongodb(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    @Override
    public VideoGame readByTitle(String title) {
        return this.videoGameRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException(" Video game title: " + title))
                .toVideoGame();
    }

    @Override
    public Stream<VideoGame> readAll() {
        return this.videoGameRepository.findAll().stream()
                .map(VideoGameEntity::toVideoGame);
    }

    @Override
    public void delete(String title) {
        this.videoGameRepository.deleteByTitle(title);
    }
}
