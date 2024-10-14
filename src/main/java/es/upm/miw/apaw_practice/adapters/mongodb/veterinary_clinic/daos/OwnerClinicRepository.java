package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.OwnerClinicEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OwnerClinicRepository extends MongoRepository<OwnerClinicEntity, String> {

    Optional<OwnerClinicEntity> findByName(String name);
}