package es.upm.miw.apaw.adapters.mongodb.football.daos;


import es.upm.miw.apaw.adapters.mongodb.football.entities.FootballPlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FootballPlayerRepository extends MongoRepository<FootballPlayerEntity, Long> {
    Optional<FootballPlayerEntity> findByNickname(String nickname);
    boolean existsByPlayerId(Long playerId);
}
