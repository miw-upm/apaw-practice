package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.GunEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GunRepository extends MongoRepository<GunEntity, Integer> {
}
