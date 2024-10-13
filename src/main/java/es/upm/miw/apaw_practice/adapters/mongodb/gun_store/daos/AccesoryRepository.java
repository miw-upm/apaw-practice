package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.AccesoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccesoryRepository extends MongoRepository<AccesoryEntity, Integer> {

}
