package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BossRepository extends MongoRepository <BossEntity, String> {
}
