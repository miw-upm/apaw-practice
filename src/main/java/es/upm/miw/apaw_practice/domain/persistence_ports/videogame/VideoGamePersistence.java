package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface VideoGamePersistence {
    Stream<VideoGame> readAll();
    VideoGame create(VideoGame videoGame);
    VideoGame update(String videoGameAlias, VideoGame videoGame);
    VideoGame read(String videoGameAlias);
    boolean exitsVideoGameAlias(String videoGameAlias);
    void delete(String videoGameAlias);
}
