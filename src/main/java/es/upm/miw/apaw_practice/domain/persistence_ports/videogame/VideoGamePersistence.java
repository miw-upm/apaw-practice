package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface VideoGamePersistence {
    VideoGame read(String videoGameAlias);
    VideoGame create(VideoGame videoGame);
    boolean existVideoGameAlias(String videoGameAlias);
    VideoGame update(String videoGameAlias, VideoGame videoGame);
    Stream<VideoGame> readAll();
    Stream<String>findPlayerNameByVideoGameAlias(String videoGameAlias);
}
