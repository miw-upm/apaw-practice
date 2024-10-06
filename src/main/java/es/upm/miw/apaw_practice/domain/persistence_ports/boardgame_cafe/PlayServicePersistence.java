package es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlayService;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PlayServicePersistence {
    Stream<PlayService> readAll();

    PlayService create(PlayService playService);

    PlayService update(Integer playServiceId, PlayService playService);

    PlayService read(Integer playServiceId);

    boolean existPlayServiceId(Integer playServiceId);
}
