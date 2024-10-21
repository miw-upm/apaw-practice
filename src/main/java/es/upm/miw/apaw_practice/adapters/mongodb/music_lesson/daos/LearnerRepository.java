package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import java.util.Optional;
import java.util.stream.Stream;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LearnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearnerRepository extends MongoRepository<LearnerEntity, String> {

  Optional<LearnerEntity> findByIdentityDocument(String identityDocument);

  Stream<LearnerEntity> findByBranchId(String id);

  void deleteByIdentityDocument(String identityDocument);
}
