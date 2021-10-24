package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.PharmacyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PharmacyRepository extends MongoRepository<PharmacyEntity, String> {

    Optional<PharmacyEntity> findByRegistrationNumber(String registrationNumber);

}
