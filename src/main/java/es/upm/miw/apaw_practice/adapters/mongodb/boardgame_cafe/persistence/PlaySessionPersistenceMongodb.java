package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.PlaySessionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlaySessionEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.PlaySessionPersistence;
import org.springframework.stereotype.Repository;

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
}
