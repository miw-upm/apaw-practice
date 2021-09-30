package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DegreeRepository extends MongoRepository<DegreeEntity, String> {
}
