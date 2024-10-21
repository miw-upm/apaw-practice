package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.TutoringSessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TutoringSessionRepository extends MongoRepository<TutoringSessionEntity, String> {
    Optional<TutoringSessionEntity> findByTitle(String tittle);
    boolean existsByTitle(String title);
    void deleteByTitle(String title);
}
