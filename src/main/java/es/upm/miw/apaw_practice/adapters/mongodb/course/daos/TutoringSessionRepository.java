package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.TutoringSessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutoringSessionRepository extends MongoRepository<TutoringSessionEntity, String> {
}
