package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import java.util.Optional;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.MusicalInstrumentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicalInstrumentRepository extends MongoRepository<MusicalInstrumentEntity, String> {

  Optional<MusicalInstrumentEntity> findByModel(String model);
}
