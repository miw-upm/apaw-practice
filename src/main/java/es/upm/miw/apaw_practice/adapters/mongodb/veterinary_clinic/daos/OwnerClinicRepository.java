package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.OwnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OwnerClinicRepository extends MongoRepository<OwnerEntity, String> {

    Optional<OwnerEntity> findByName(String name);
}