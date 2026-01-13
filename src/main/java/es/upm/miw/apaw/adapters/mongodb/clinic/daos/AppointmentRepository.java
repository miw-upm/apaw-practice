package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends MongoRepository<AppointmentEntity, UUID> {

    @Query("{ 'diagnoses.code': ?0 }")
    List<AppointmentEntity> findByDiagnosisCode(String code);
}
