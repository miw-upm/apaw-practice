package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BossRepository extends MongoRepository <BossEntity, String> {
    List<BossEntity> findByEffort(String effort);
    List<BossEntity> findByDescription(String description);
    int deleteByDescriptionAndEffort(String description,String effort);
}
