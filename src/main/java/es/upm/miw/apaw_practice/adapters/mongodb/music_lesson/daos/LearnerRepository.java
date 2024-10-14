package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import java.util.Optional;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LearnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearnerRepository extends MongoRepository<LearnerEntity, String> {

  Optional<LearnerEntity> findByIdentityDocument(String identityDocument);

  void deleteByIdentityDocument(String identityDocument);
}
