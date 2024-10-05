package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.MuseumEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MuseumRepository extends MongoRepository<MuseumEntity, String> {

}
