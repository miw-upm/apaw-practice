package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.FighterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FighterRepository extends MongoRepository<FighterEntity, String> {
}