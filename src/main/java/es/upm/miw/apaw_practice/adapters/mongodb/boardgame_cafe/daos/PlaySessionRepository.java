package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlaySessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface PlaySessionRepository extends MongoRepository<PlaySessionEntity, String> {
    Optional<PlaySessionEntity> findByPlaySessionId(Integer playSessionId);
}
