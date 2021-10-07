package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.RaidEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RaidRepository extends MongoRepository <RaidEntity, String> {
}
