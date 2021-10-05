package es.upm.miw.apaw_practice.adapters.mongodb.football.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.PrincipalRefereeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrincipalRefereeRepository extends MongoRepository<PrincipalRefereeEntity, String> {
}
