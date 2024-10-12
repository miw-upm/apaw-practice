package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.CompatibleAmmoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompatibleAmmoRepository extends MongoRepository<CompatibleAmmoEntity, Integer> {
}
