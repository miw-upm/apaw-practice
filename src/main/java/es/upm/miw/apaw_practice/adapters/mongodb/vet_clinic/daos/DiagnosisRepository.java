package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.DiagnosisEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DiagnosisRepository extends MongoRepository<DiagnosisEntity, String> {
    Optional<DiagnosisEntity> findDiagnosisByCritical(Boolean critical);
}
