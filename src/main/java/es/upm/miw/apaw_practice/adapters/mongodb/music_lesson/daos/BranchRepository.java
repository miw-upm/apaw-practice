package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import java.util.Optional;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.BranchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<BranchEntity, String> {

  Optional<BranchEntity> findByCode(String code);
}
