package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.TreatmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TreatmentRepository extends MongoRepository<TreatmentEntity, String> {

    // Método para buscar por la clave de negocio (treatmentCode)
    Optional<TreatmentEntity> findByTreatmentCode(String treatmentCode);
}