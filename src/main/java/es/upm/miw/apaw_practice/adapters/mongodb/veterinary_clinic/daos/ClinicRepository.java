package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.ClinicEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClinicRepository extends MongoRepository<ClinicEntity, String> {

    Optional<ClinicEntity> findByName(String name);

    int deleteByName(String name);
}