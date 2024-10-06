package es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface GamePersistence {
    Stream<Game> readAll();

    Game create(Game game);

    Game update(String gameName, Game game);

    Game read(String gameName);

    boolean existGameName(String gameName);
}
