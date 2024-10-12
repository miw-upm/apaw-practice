package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.SetupEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SetupRepository extends MongoRepository<SetupEntity, Integer> {
}
