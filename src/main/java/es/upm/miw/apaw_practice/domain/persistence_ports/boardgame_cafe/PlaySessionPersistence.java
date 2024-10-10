package es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PlaySessionPersistence {
    Stream<PlaySession> readAll();

    PlaySession create(PlaySession playSession);

    PlaySession update(Integer playSessionId, PlaySession playSession);

    PlaySession read(Integer playSessionId);

    boolean existPlaySessionId(Integer playSessionId);
}
