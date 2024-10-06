package es.upm.miw.apaw_practice.adapters.mongodb.military.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.WeaponEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeaponRepository extends MongoRepository<WeaponEntity, String> {
}
