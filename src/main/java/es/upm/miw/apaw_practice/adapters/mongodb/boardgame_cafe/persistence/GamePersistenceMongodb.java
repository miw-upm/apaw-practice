package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.GameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.GamePersistence;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public class GamePersistenceMongodb implements GamePersistence {

    private final GameRepository gameRepository;

    public GamePersistenceMongodb(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Stream<Game> readAll() {
        return gameRepository
                .findAll()
                .stream()
                .map(GameEntity::toGame);
    }

    @Override
    public Game create(Game game) {
        return gameRepository
                .save(new GameEntity(game))
                .toGame();
    }

    @Override
    public Game update(String gameName, Game game) {
        GameEntity gameEntity = gameRepository
                .findByGameName(gameName)
                .orElseThrow(() -> new NotFoundException("Game Name: " + gameName));
        gameEntity.fromGame(game);
        return gameRepository
                .save(gameEntity)
                .toGame();
    }

    @Override
    public Game read(String gameName) {
        return gameRepository
                .findByGameName(gameName)
                .orElseThrow(() -> new NotFoundException("Game Name: " + gameName))
                .toGame();
    }

    @Override
    public void delete(String gameName) {
        gameRepository.deleteByGameName(gameName);

    }

    @Override
    public boolean existGameName(String gameName) {
        return gameRepository
                .findByGameName(gameName)
                .isPresent();
    }
}
