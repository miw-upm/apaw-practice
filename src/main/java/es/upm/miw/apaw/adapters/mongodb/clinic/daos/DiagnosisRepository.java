package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DiagnosisEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiagnosisRepository extends MongoRepository<DiagnosisEntity, String> {
    // List<DiagnosisEntity> findByAnimalId(String animalId);
}