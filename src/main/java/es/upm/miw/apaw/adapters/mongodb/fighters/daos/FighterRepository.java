package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.FighterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FighterRepository extends MongoRepository<FighterEntity, String> {
    Optional<FighterEntity> findByNickname(String nickname);
}