package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.PharmacyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PharmacyRepository extends MongoRepository<PharmacyEntity, String> {

}
