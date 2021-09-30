package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.PetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<PetEntity, String> {
}
