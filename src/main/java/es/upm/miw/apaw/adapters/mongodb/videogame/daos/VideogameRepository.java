package es.upm.miw.apaw.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface VideogameRepository extends MongoRepository<VideogameEntity, UUID> {
}
