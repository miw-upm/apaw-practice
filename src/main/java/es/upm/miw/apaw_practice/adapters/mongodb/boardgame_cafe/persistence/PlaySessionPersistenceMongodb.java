package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.PlaySessionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlaySessionEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.PlaySessionPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class PlaySessionPersistenceMongodb implements PlaySessionPersistence {

    private final PlaySessionRepository playSessionRepository;

    public PlaySessionPersistenceMongodb(PlaySessionRepository playSessionRepository) {
        this.playSessionRepository = playSessionRepository;
    }

    @Override
    public Stream<PlaySession> readAll() {
        return playSessionRepository
                .findAll()
                .stream()
                .map(PlaySessionEntity::toPlaySession);
    }

    @Override
    public PlaySession create(PlaySession playSession) {
        return playSessionRepository
                .save(new PlaySessionEntity(playSession))
                .toPlaySession();
    }

    @Override
    public PlaySession update(Integer playSessionId, PlaySession playSession) {
        PlaySessionEntity playSessionEntity = playSessionRepository
                .findByPlaySessionId(playSessionId)
                .orElseThrow(() -> new NotFoundException("Play Session Id: " + playSessionId));
        playSessionEntity.fromPlaySession(playSession);
        return playSessionRepository
                .save(playSessionEntity)
                .toPlaySession();
    }

    @Override
    public PlaySession read(Integer playSessionId) {
        return playSessionRepository
                .findByPlaySessionId(playSessionId)
                .orElseThrow(() -> new NotFoundException("Play Session Id: " + playSessionId))
                .toPlaySession();
    }

    @Override
    public boolean existPlaySessionId(Integer playSessionId) {
        return playSessionRepository
                .findByPlaySessionId(playSessionId)
                .isPresent();
    }

    @Override
    public Integer findTotalMembershipDurationByGameGenre(String genre) {
        List<PlaySessionEntity> playSessions = this.playSessionRepository.findAll();

        return playSessions.stream()
                .filter(playSession -> playSession.getSelectedGamesEntities().stream()
                        .anyMatch(game -> genre.equals(game.getGenre())))
                .flatMap(playSession -> playSession.getCustomers().stream())
                .filter(customer -> customer.getMembership() != null)
                .mapToInt(customer -> customer.getMembership().getDuration())
                .sum();
    }

    @Override
    public List<Game> findGamesByMembershipType(String type) {
        List<PlaySessionEntity> playSessions = this.playSessionRepository.findAll();

        return playSessions.stream()
                .filter(playSession -> playSession.getCustomers().stream()
                        .anyMatch(customer -> customer.getMembership() != null && type.equals(customer.getMembership().getType())))
                .flatMap(playSession -> playSession.getSelectedGamesEntities().stream())
                .map(GameEntity::toGame)
                .distinct()
                .collect(Collectors.toList());
    }
}