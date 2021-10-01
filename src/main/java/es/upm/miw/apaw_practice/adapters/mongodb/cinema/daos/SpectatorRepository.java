package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.SpectatorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpectatorRepository extends MongoRepository<SpectatorEntity, String> {
}
