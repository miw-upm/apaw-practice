package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DirectorRepository extends MongoRepository<DirectorEntity, String> {
}
