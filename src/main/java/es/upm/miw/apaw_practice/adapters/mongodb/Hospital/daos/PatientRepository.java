package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository extends MongoRepository<PatientEntity, String> {
    void deleteByDni(String dni);
    Optional<PatientEntity> findByDni(String dni);

    List<PatientEntity> findByDniIn(List<String> dniList);

}
