package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ZooRepository extends MongoRepository<ZooEntity, String> {
}
