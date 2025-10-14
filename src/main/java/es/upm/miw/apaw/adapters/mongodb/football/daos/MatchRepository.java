package es.upm.miw.apaw.adapters.mongodb.football.daos;


import es.upm.miw.apaw.adapters.mongodb.football.entities.MatchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MatchRepository extends MongoRepository<MatchEntity, Long> {
    Optional<MatchEntity> findByMatchId(Long matchId);
    boolean existsByMatchId(Long matchId);
}
