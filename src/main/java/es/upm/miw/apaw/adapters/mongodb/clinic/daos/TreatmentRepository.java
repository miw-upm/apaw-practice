package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.TreatmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreatmentRepository extends MongoRepository<TreatmentEntity, String> {
    // List<TreatmentEntity> findByDiagnosisId(String diagnosisId);
}
