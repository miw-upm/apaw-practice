package es.upm.miw.apaw_practice.domain.services.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.GamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class GameService {

    private final GamePersistence gamePersistence;

    @Autowired
    public GameService(GamePersistence gamePersistence) {
        this.gamePersistence = gamePersistence;
    }

    public Stream<Game> readAllGames() {
        return this.gamePersistence.readAll();
    }

    public void deleteGame(String gameName) {
        this.gamePersistence.delete(gameName);
    }
}
