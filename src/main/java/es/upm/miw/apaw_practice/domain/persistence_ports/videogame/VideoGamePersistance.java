package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface VideoGamePersistance {

    VideoGame readByTitle(String title);

    void delete(String title);

    Stream<VideoGame> readAll();
}
