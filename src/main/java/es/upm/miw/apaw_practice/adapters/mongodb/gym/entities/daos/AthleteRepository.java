package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.AthleteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AthleteRepository  extends MongoRepository<AthleteEntity,String> {
}
