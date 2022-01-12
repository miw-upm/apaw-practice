package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.ParticipantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParticipantRepository extends MongoRepository<ParticipantEntity, String> {
}
