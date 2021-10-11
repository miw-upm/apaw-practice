package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BossRepository extends MongoRepository <BossEntity, String> {

    Optional<BossEntity> findByEffort (String effort);
}
