package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DiagnosisEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DiagnosisRepository extends MongoRepository<DiagnosisEntity, String> {

    // Método para buscar por la clave de negocio (code)
    Optional<DiagnosisEntity> findByCode(String code);
}