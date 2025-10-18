package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.ClassSessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassSessionRepository extends MongoRepository<ClassSessionEntity, Integer> {
}
