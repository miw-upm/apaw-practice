package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.GunEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GunRepository extends MongoRepository<GunEntity, Integer> {
    Optional<GunEntity> findByName(String name);
}
