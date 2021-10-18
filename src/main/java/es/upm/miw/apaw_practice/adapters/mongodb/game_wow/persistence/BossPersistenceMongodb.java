package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.BossRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.BossPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;
@Repository("bossPersistence")
public class BossPersistenceMongodb implements BossPersistence {

    private final BossRepository bossRepository;

    @Autowired
    public BossPersistenceMongodb(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    @Override
    public Stream<Boss> findByEffort(String effort){
        return this.bossRepository.findByEffort(effort).stream()
                .map(BossEntity::toBoss);
    }

    @Override
    public void delete(String description, String effort) {
        this.bossRepository.deleteByDescriptionAndEffort(description, effort);
    }
}
