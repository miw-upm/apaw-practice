package es.upm.miw.apaw_practice.adapters.mongodb.military.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.SoldierEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SoldierRepository extends MongoRepository<SoldierEntity, String> {
}
